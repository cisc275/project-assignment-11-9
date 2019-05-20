//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;

public enum GameState {
	
	TITLE("title", 0),
	OSPREY("osprey", 1),
	HARRIER("harrier", 2);
	
	private String name;
	private int num;
	
	private GameState(String s, int i) { name = s; num = i; }
	
	public String getName() { return name; }
	
	public int getNum() { return num; }
}