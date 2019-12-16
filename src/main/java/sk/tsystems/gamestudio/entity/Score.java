package sk.tsystems.gamestudio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Score {

	
	@Id
	@GeneratedValue
	
	private int ident;
	

	private String username;
	
	private String game;
	
	private int value;
	
	@Override
	public String toString() {
		return "Score [ident=" + ident + ", username=" + username + ", game=" + game + ", value=" + value + "]";
	}

	public Score() {
		}

	public Score(String userName, String game, int value) {
		this.username = userName;
		this.game = game;
		this.value = value;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}






	
	

}
