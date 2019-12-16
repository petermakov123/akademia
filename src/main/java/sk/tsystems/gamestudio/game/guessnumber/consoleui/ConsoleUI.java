package sk.tsystems.gamestudio.game.guessnumber.consoleui;

import java.util.Random;
import java.util.Scanner;

public class ConsoleUI {

	public void newGameStarted() {
	
		processInput();
	}
	
	
	private void processInput() {
		int random, guess;
		Scanner keyboard = new Scanner(System.in);
		Random generator = new Random();
		random = generator.nextInt(10) + 1;

		System.out.println("I thinking of a number between 0-10 ");
		long startMillis = System.currentTimeMillis();
		do {
			
			
			
			guess = keyboard.nextInt();

			if (guess > random) {
				System.out.println("LOWER!!");

			} if(guess<random) {
				System.out.println("HIGHER!!");

			}
		} while (guess != random);
		long seconds = (System.currentTimeMillis() - startMillis)/1000;
		System.out.println("RIGHT "+seconds);
		System.out.println("YOUR SCORE IS  "+seconds);
		keyboard.close();
		
	
	
	}
	}

//long startMillis = System.currentTimeMillis();
//long seconds = (System.currentTimeMillis() - startMillis)
