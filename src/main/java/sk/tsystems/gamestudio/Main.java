package sk.tsystems.gamestudio;

import sk.tsystems.gamestudio.consoleui.Menu;
import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.service.ScoreService;
import sk.tsystems.gamestudio.service.ScoreServiceJDBC;

public class Main {

	public static void main(String[] args) {
		ScoreService scoreService = new ScoreServiceJDBC();
		
		for(Score score : scoreService.getTopScores("npuzzle")) {
			System.out.println(score.getUserName()+"  " + score.getValue() );
		}
//
//		
//		scoreService.addScore(new Score("jozo", "npuzzle", 453));
//		scoreService.addScore(new Score("fero", "npuzzle", 173));
//		scoreService.addScore(new Score("matoo", "npuzzle", 183));
//		scoreService.addScore(new Score("pato", "npuzzle", 133));
//		scoreService.addScore(new Score("pto", "npuzzle", 623));
//		scoreService.addScore(new Score("padso", "npuzzle", 423));
//		scoreService.addScore(new Score("peasd", "npuzzle", 143));
//		scoreService.addScore(new Score("petoas", "npuzzle", 183));
//		scoreService.addScore(new Score("klra", "npuzzle", 153));
//		scoreService.addScore(new Score("robo", "npuzzle", 13));
//		scoreService.addScore(new Score("stano", "npuzzle", 15));
//		scoreService.addScore(new Score("kefo", "npuzzle", 152));
		

		Menu userInterface = new Menu();
		userInterface.runGamestudio();

	}

}
