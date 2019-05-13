//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

package Project;
/* 
 * Public class Mouse contains the behaviors and attributes of Mice, the primary consumables in the Harrier game.
 */
public class Mouse extends Animal implements java.io.Serializable {

	public Mouse(double x, double y) {
		setXPos(x);
		setYPos(y);
		setXWidth(20);
		setYWidth(20);
		setXVel(3);
		setYVel(2);
		setSpeedMod(3);
		updateDirection();
	}
	
	/*
	 * public method interact.
	 * Takes Harrier as parameter and returns nothing.
	 * Processes an interaction between the Harrier and the Mouse.
	 */
	public void interact(Harrier h) {
		h.mouseHit = true;
		h.setScore(h.getScore() + 10);
		if (h.getVision() + 25 < HarrierModel.MAX_VISION) {
			h.setVision(h.getVision() + 25);
		} else {
			h.setVision(HarrierModel.MAX_VISION);
		}
	}
	
}