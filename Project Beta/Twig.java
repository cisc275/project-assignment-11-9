//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;

/* 
 * Public class Twig contains the behaviors and attributes of Twigs, the primary consumables in the Harrier game.
 */
public class Twig extends GameObject {
	
	public Twig(double x, double y) {
		setXPos(x);
		setYPos(y);
		setXWidth(40);
		setYWidth(25);
	}
	
	/*
	 * public method interact.
	 * Takes Harrier as parameter and returns nothing.
	 * Processes an interaction between the Harrier and the Twig.
	 */
	public void interact(Harrier h) {
		h.setScore(h.getScore() + 20);
		if(h.getVision() + 12.5 < Harrier.MAX_VISION) { h.setVision(h.getVision() + 12.5); }
		else { h.setVision(Harrier.MAX_VISION); }
	}
	
}
