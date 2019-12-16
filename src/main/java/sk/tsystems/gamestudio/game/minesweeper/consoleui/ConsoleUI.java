package sk.tsystems.gamestudio.game.minesweeper.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.game.minesweeper.UserInterface;
import sk.tsystems.gamestudio.game.minesweeper.core.Clue;
import sk.tsystems.gamestudio.game.minesweeper.core.Field;
import sk.tsystems.gamestudio.game.minesweeper.core.GameState;
import sk.tsystems.gamestudio.game.minesweeper.core.Mine;
import sk.tsystems.gamestudio.game.minesweeper.core.Tile;
import sk.tsystems.gamestudio.service.ScoreService;
import sk.tsystems.gamestudio.service.ScoreServiceJDBC;

public class ConsoleUI implements UserInterface {

	private Field field;

	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	private String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public void newGameStarted(Field field) {
		this.field = field;
		long startMillis = System.currentTimeMillis();
		do {
			update();
		if (processInput()) {
	return;
		}
			if (field.getState() == GameState.SOLVED) {
				update();
				long seconds = (System.currentTimeMillis() - startMillis) / 1000;
				int scoreTime = (int) seconds;
				System.out.println("YOU WIN  " + seconds);
				ScoreService scoreService = new ScoreServiceJDBC();
				scoreService.addScore(new Score("peto", "minesweeper", scoreTime));
				
			}
			if (field.getState() == GameState.FAILED) {
				update();
				System.out.println("MINE!!!! YOU LOSE");
				
			}

		} while (!field.isSolved());
	}

	@Override
	public void update() {
		System.out.print("  ");
		for (int i = 0; i < field.getColumnCount(); i++) {
			System.out.printf("%c ", '0' + i);
		}
		System.out.println();

		for (int i = 0; i < field.getRowCount(); i++) {
			System.out.printf("%c ", 'A' + i);
			for (int j = 0; j < field.getColumnCount(); j++) {
				if (field.getTile(i, j).getState() == Tile.State.OPEN) {
					if (field.getTile(i, j) instanceof Mine) {
						System.out.printf("X ");
					} else if (field.getTile(i, j) instanceof Clue) {

						System.out.printf("%d ", ((Clue) field.getTile(i, j)).getValue());

					}
				} else if (field.getTile(i, j).getState() == Tile.State.MARKED) {
					System.out.printf("%c ", 'M');
				} else if (field.getTile(i, j).getState() == Tile.State.CLOSED) {
					System.out.printf("- ");
				}

			}
			System.out.println();
		}

	}

	boolean handleInput(String input) throws WrongFormatException {
		input = input.toUpperCase();
		Pattern pattern = Pattern.compile("(O|M)([A-Z])([0-9])");
		Matcher matcher = pattern.matcher(input);
		boolean matches = matcher.matches();
		if (matches == false) {
			System.out.println("Wrong input, select again");
			return false;
		}
		

		int inRow = matcher.group(2).charAt(0) - 'A';
		int inColumn = matcher.group(3).charAt(0) - '0';

		if (matcher.group(1).equals("O")) {
			field.openTile(inRow, inColumn);
		}
		if (matcher.group(1).equals("M")) {
			field.markTile(inRow, inColumn);
		}
		return false;
	}

	private boolean processInput() {

		System.out.println("Enter you selection (X) EXIT, (MA1) MARK, (OB4) OPEN, ");
		String inputt = readLine().toUpperCase();
		try {
			if (inputt.equals("X")) {
				System.out.println("EXIT PROGRAM");
				return true;
			}
			handleInput(inputt);
		} catch (WrongFormatException ex) {
			System.err.println(ex.getMessage());
		}
		return false;
	}
}

// input = input.toUpperCase();
//		Pattern pattern = Pattern.compile("(O|M)([A-Z])([0-9])");
//		Matcher matcher = pattern.matcher(input);
//		boolean matches = matcher.matches();
//		if (matches == false) {
//			System.out.println("Wrong input, select again");
//			return;
//		}
//		if (input.equals("X")) {
//			System.out.println("EXIT PROGRAM");
//			System.exit(0);
//		}
//
//		int inRow = matcher.group(2).charAt(0) - 'A';
//		int inColumn = matcher.group(3).charAt(0) - '0';
//
//		if (matcher.group(1).equals("O")) {
//			field.openTile(inRow, inColumn);
//		}
//		if (matcher.group(1).equals("M")) {
//			field.markTile(inRow, inColumn);
//		}
//
//	}
