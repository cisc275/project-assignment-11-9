//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;

/* 
 * Public class Mouse contains the behaviors and attributes of Mice, the primary consumables in the Harrier game.
 */
public class Mouse extends Animal {
	
	private final static int SIZE = 25;
	private final static double SPEED = 1.5;
	private final static int CHANCE = 30;
	private final static int SCORE_BOOST = 10;
	private final static int VISION_BOOST = 25;

	public Mouse(double x, double y) {
		setXPos(x);
		setYPos(y);
		setXWidth(SIZE);
		setYWidth(SIZE);
		setSpeedMod(SPEED);
		setChanceMod(CHANCE);
	}
	
	/*
	 * public method interact.
	 * Takes Harrier as parameter and returns nothing.
	 * Processes an interaction between the Harrier and the Mouse.
	 */
	public void interact(Harrier h) {
		h.setScore(h.getScore() + SCORE_BOOST);
		if(h.getVision() + VISION_BOOST < Harrier.MAX_VISION) { h.setVision(h.getVision() + VISION_BOOST); }
		else { h.setVision(Harrier.MAX_VISION); }
	}
	
	/*
	 * public method roam.
	 * Takes no parameters and returns nothing.
	 * Makes the mouse possibly change direction and move.
	 */
	public void roam(double xBound, double yBound, int velMultiplier) {
		twitch();
		moveBounded(xBound, yBound, velMultiplier);
	}
	
}