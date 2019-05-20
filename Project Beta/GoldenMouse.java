//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;

/* 
 * Public class GoldenMouse contains the behaviors and attributes of Golden Mice, the special power-up in the Harrier game.
 */
public class GoldenMouse extends Mouse {
	
	boolean tutorial = false;
	boolean correct = false;
	
	public GoldenMouse(double x, double y) {
		super(x, y);
		setSpeedMod(5);
	}
	public GoldenMouse(double x, double y, boolean tutorial) {
		super(x, y);
		setSpeedMod(5);
		this.tutorial = tutorial;
	}
	
	/*
	 * public method interact.
	 * Takes Harrier as parameter and returns nothing.
	 * Processes an interaction between the Harrier and the GoldenMouse if the player correctly answers the triggerd quiz.
	 */
	public void interact(Harrier h) {
		Quiz quiz = new Quiz(this);
		if (correct) {
			h.setScore(h.getScore() + 100);
			if(h.getVision() + 250 < Harrier.MAX_VISION) { h.setVision(h.getVision() + 250); }
			else { h.setVision(Harrier.MAX_VISION); }
		}
	}
	
}