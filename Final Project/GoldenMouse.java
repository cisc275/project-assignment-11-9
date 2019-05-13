//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;

/* 
 * Public class GoldenMouse contains the behaviors and attributes of Golden Mice, the special power-up in the Harrier game.
 */
public class GoldenMouse extends Mouse implements java.io.Serializable {
	String answer = "1";
	String correct = "2";
	public GoldenMouse(double x, double y) {
		super(x, y);
		setSpeedMod(10);
	}
	
	/*
	 * public method interact.
	 * Takes Harrier as parameter and returns nothing.
	 * Processes an interaction between the Harrier and the GoldenMouse.
	 */
	public void interact(Harrier h) {
		Quiz quiz = new Quiz(this);
		if (answer.equals(correct)) {
			h.setScore(h.getScore() + 100);
			h.setVision(h.getVision() + 250);
		}
	}
	
}