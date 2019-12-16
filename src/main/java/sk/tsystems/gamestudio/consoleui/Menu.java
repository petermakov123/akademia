package sk.tsystems.gamestudio.consoleui;

import java.util.Scanner;

import sk.tsystems.gamestudio.game.guessnumber.GuessNumber;
import sk.tsystems.gamestudio.game.minesweeper.Minesweeper;
import sk.tsystems.gamestudio.game.puzzle.Puzzle;

public class Menu {

	Scanner in = new Scanner(System.in);

	public void runGamestudio() {
do {
		showMenu();
		selectGame();
}while(true);
}


	private void showMenu() {
		System.out.println("CHOSE THE GAME PLEASE");
		System.out.println("1. Minesweeper");
		System.out.println("2. GuessNumber");
		System.out.println("3. NPuzzle");
		System.out.println("4. EXIT");
	}

	private boolean selectGame() {

		int num = in.nextInt();
		switch (num) {
		case 1:
			new Minesweeper();
			return true;
		case 2:
			new GuessNumber();
			return true;
		case 3:
			new Puzzle();
			return true;
		case 4:
			return false;
			
		default: return true;
	

		}
	}
}
