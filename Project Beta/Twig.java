//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
/* 
 * Public class Twig contains the behaviors and attributes of Twigs, the secondary consumables in the Harrier game.
 */
public class Twig extends GameObject {
	
	private final static int WIDTH = 40;
	private final static int HEIGHT = 25;
	private final static int SCORE_BOOST = 20;
	public final static double VISION_BOOST = 12.5;
	
	public Twig(double x, double y) {
		setXPos(x);
		setYPos(y);
		setXWidth(WIDTH);
		setYWidth(HEIGHT);
	}
	
	/*
	 * public method interact.
	 * Parameters:
	 *     Harrier: h
	 * Returns: nothing
	 * Processes an interaction between the Harrier and the Twig.
	 */
	public void interact(Harrier h) {
		h.setScore(h.getScore() + SCORE_BOOST);
		if(h.getVision() + VISION_BOOST < Harrier.MAX_VISION) { h.setVision(h.getVision() + VISION_BOOST); }
		else { h.setVision(Harrier.MAX_VISION); }
	}
	
}
