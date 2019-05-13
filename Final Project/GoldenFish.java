//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;

/* 
 * Public class GoldenFish contains the behaviors and attributes of Golden Fish, the special power-up in the Osprey game.
 */
public class GoldenFish extends Fish implements java.io.Serializable {
	String answer = "1";
	String correct = "2";
	
	public GoldenFish(double x, double y) {
		super(x, y, 1);
	}
	
	/*
	 * public method interact.
	 * Takes Osprey as parameter and returns nothing.
	 * Processes an interaction between the Osprey and the GoldenFish.
	 */
	public void interact(Osprey o) {
		Quiz quiz = new Quiz(this);
		if (answer.equals(correct))
			o.setXVel(o.getXVel() + 10);
	}
	
}
