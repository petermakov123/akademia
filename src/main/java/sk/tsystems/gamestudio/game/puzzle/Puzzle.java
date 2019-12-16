package sk.tsystems.gamestudio.game.puzzle;

import sk.tsystems.gamestudio.game.puzzle.consoleui.ConsoleUI;
import sk.tsystems.gamestudio.game.puzzle.core.Field;

public class Puzzle {
	
	
	public Puzzle() {
		ConsoleUI ui = new ConsoleUI();
		Field field = new Field(10, 10);
		ui.play(field);
		
	}

	public static void main(String[] args) {
		
		new Puzzle();
	
	}
}
