//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
/* 
 * Public class Mouse contains the behaviors and attributes of Mice, the primary consumables in the Harrier game.
 */
public class Mouse extends Animal implements HarrierAble {

	public Mouse(double x, double y) {
		setXPos(x);
		setYPos(y);
		setXWidth(20);
		setYWidth(20);
		setXVel(2);
		setYVel(3);
		updateDirection();
	}
	
	/*
	 * public method interact.
	 * Takes Harrier as parameter and returns nothing.
	 * Processes an interaction between the Harrier and the Mouse.
	 */
	public void interact(Harrier h) {
		h.setScore(h.getScore() + 10);
		h.setVision(h.getVision() + 1);
	}
	
}
