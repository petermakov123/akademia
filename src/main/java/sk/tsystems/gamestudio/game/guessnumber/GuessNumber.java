package sk.tsystems.gamestudio.game.guessnumber;

import sk.tsystems.gamestudio.game.guessnumber.consoleui.ConsoleUI;

public class GuessNumber {

	
	public GuessNumber() {
		ConsoleUI userInterface = new ConsoleUI();
		userInterface.newGameStarted();	
	}
	
	
	public static void main(String[] args) {
	new GuessNumber();
		
	}
}
