//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;

public enum GameState {
	
	TITLE("title"),
	OSPREY("osprey"),
	HARRIER("harrier");
	
	private String name = null;
	
	private GameState(String s) { name = s; }
	
	public String getName() { return name; }
	
}
