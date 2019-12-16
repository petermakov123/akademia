package sk.tsystems.gamestudio.controller;

import java.util.Formatter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.game.minesweeper.core.Clue;
import sk.tsystems.gamestudio.game.minesweeper.core.Field;
import sk.tsystems.gamestudio.game.minesweeper.core.GameState;
import sk.tsystems.gamestudio.game.minesweeper.core.Mine;
import sk.tsystems.gamestudio.game.minesweeper.core.Tile;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MinesweeperController {

	private Field field;

	private boolean marking;

	public boolean isMarking() {
		return marking;
	}

	@RequestMapping("/minesweeper")
	public String index() {
		field = new Field(10, 10, 5);
		return "minesweeper";
	}

	@RequestMapping("/minesweeper/open")
	public String open(int row, int column) {
		if (field.getState() == GameState.PLAYING)
			if (marking)
				field.openTile(row, column);
			else
			field.markTile(row, column);

		return "minesweeper";
	}

	@RequestMapping("/minesweeper/change")
	public String change() {

		marking = !marking;

		return "minesweeper";
	}

	public String getHtmlField() {
		@SuppressWarnings("resource")
		Formatter ft = new Formatter();
		ft.format("<table>\n");
		for (int row = 0; row < field.getRowCount(); row++) {
			ft.format("<tr>\n");
			for (int column = 0; column < field.getColumnCount(); column++) {
				ft.format("<td>\n");
				Tile tile = field.getTile(row, column);
				if (tile.getState() == Tile.State.CLOSED) {
					ft.format(
							"<a href='/minesweeper/open?row=%d&column=%d'><img src='/images/minesweeper/MINESWEEPER_X.png' alt='Closed minefield tile.'/></a>",
							row, column);
				} else if (tile.getState() == Tile.State.MARKED) {
					ft.format(
							"<a href='/minesweeper/change?row=%d&column=%d'><img src='/images/minesweeper/MINESWEEPER_F.png' alt='Marked minefield tile.'/></a>",
							row, column);
				} else {
					if (tile instanceof Mine) {
						ft.format("<img src='/images/minesweeper/MINESWEEPER_M.png' alt='Explode mine.'/>");
					}

					if (tile instanceof Clue) {
						ft.format(
								"<a href='/minesweeper/open?row=%d&column=%d'><img src='/images/minesweeper/MINESWEEPER_%d.png' alt='Opened minefield tile.'/></a>",
								row, column, ((Clue) tile).getValue());

					}
				}
				ft.format("</td>\n");
			}
			ft.format("</tr>\n");
		}
		ft.format("</table>\n");
		return ft.toString();
	}
	/*
	 * private String getImageName(Tile tile) { switch (tile.getState()) { case
	 * CLOSED: return ""; case MARKED: return""; case OPEN: if(tile instanceof Clue)
	 * return"" + ((Clue)tile).getValue(); else return""; default:
	 * 
	 * } }
	 */
}
