package sk.tsystems.gamestudio.controller;

import java.util.Formatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.game.puzzle.core.Field;
import sk.tsystems.gamestudio.game.puzzle.core.Tile;
import sk.tsystems.gamestudio.service.ScoreService;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class PuzzleController {
	private Field field;

	@Autowired
	private ScoreService scoreService;
	@Autowired
	private MainController mainControler;

	@RequestMapping("/puzzle")
	public String index() {
		
		field = new Field(4, 4);
		return "puzzle";
	}

	@RequestMapping("/puzzle/move")
	public String move(int tile) {
		field.move(tile);
		if(field.isSolved()&& mainControler.isLogged()) {
			scoreService.addScore(new Score(mainControler.getLoggedPlayer().getName(),"puzzle",field.getScore(1,100)));
		}
		return "puzzle";
	}

	public String getHtmlField() {
		@SuppressWarnings("resource")
		Formatter f = new Formatter();
		f.format("<table>\n");
		for (int row = 0; row < field.getRowCount(); row++) {
			f.format("<tr>\n");
			for (int column = 0; column < field.getColumnCount(); column++) {
				f.format("<td>\n");
				Tile tile = field.getTile(row, column);
				if (tile != null)
					f.format("<a href='/puzzle/move?tile=%d'><img src='/images/puzzle/img%d.jpg'></a>", tile.getValue(),
							tile.getValue());
				f.format("</td>\n");

			}
			f.format("</tr>\n");
		}
		f.format("</table>\n");

		return f.toString();
	}

	public boolean isSolved() {
		return field.isSolved();
	}
public List<Score> getScores(){
	return scoreService.getTopScores("puzzle");
}
}
