//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;

/* 
 * Public class Mouse contains the behaviors and attributes of Mice, the primary consumables in the Harrier game.
 */
public class Mouse extends Animal {

	public Mouse(double x, double y) {
		setXPos(x);
		setYPos(y);
		setXWidth(25);
		setYWidth(25);
		setSpeedMod(1.5);
		setChanceMod(30);
	}
	
	/*
	 * public method interact.
	 * Takes Harrier as parameter and returns nothing.
	 * Processes an interaction between the Harrier and the Mouse.
	 */
	public void interact(Harrier h) {
		h.setScore(h.getScore() + 10);
		if(h.getVision() + 25 < Harrier.MAX_VISION) { h.setVision(h.getVision() + 25); }
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