//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

package Project;

/* 
 * Public class GoldenMouse contains the behaviors and attributes of Golden Mice, the special power-up in the Harrier game.
 */
public class GoldenMouse extends Mouse implements HarrierAble {
	
	public GoldenMouse(double x, double y) {
		super(x, y);
	}
	
	/*
	 * public method interact.
	 * Takes Harrier as parameter and returns nothing.
	 * Processes an interaction between the Harrier and the GoldenMouse.
	 */
	public void interact(Harrier h) {
		h.setScore(h.getScore() + 100);
		h.setVision(h.getVision() + 10);
	}
	
}
