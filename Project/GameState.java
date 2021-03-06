//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
/*
 * public enum GameState defines the four possible states that the game can be in.
 */
public enum GameState {
	
	TITLE("title", 0),
	OSPREY("osprey", 1),
	HARRIER("harrier", 2),
	END("end", 3);
	
	private String name;
	private int num;
	
	private GameState(String s, int i) { name = s; num = i; }
	
	public String getName() { return name; }
	
	public int getNum() { return num; }
}
