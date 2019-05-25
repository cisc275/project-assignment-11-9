//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
/* 
 * Public class Tree contains the behaviors and attributes of Trees, the obstacles in the Harrier game.
 */
public class Tree extends GameObject {
	
	long lastTime;
	private final static int SIZE = 90;
	private final static int NANOSECONDS_TO_SECONDS = 1000000000;
	private final static int TREE_DELAY = 1;
	private final static int SCORE_HIT = 10;
	private final static int VISION_HIT = 25;
	
	public Tree(double x, double y) {
		setXPos(x);
		setYPos(y);
		setXWidth(SIZE);
		setYWidth(SIZE);
	}
	
	/*
	 * public method interact.
	 * Parameters:
	 *     Harrier: h
	 * Returns: nothing
	 * Processes an interaction between the Harrier and the Tree if a second has elapsed since last hit.
	 */
	public void interact(Harrier h) {
		h.setXPos(h.getXPos() - h.getXVel());
		h.setYPos(h.getYPos() - h.getYVel());
		long newTime = System.nanoTime();
		if ((newTime - lastTime) / NANOSECONDS_TO_SECONDS > TREE_DELAY) {
			h.setScore(h.getScore() - SCORE_HIT);
			h.setVision(h.getVision() - VISION_HIT);
			lastTime = newTime;
		} 
		h.setXVel(0);
		h.setYVel(0);
	}
	
}
