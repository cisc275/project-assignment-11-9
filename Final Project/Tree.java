//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

package Project;
/* 
 * Public class Tree contains the behaviors and attributes of Trees, the obstacles in the Harrier game.
 */
public class Tree extends GameObject {
	
	long lastTime;
	
	public Tree(double x, double y) {
		setXPos(x);
		setYPos(y);
		setXWidth(120);
		setYWidth(120);
	}
	
	/*
	 * public method interact.
	 * Takes Harrier as parameter and returns nothing.
	 * Processes an interaction between the Harrier and the Tree.
	 */
	public void interact(Harrier h) {
		long newTime = System.nanoTime();
		if ((newTime - lastTime) / 1000000000 > 1) {
			h.setScore(h.getScore() - 10);
			if (h.getVision() - 25 > HarrierModel.MIN_VISION) {
				h.setVision(h.getVision() - 25);
			} else {
				h.setVision(HarrierModel.MIN_VISION);
			}
			lastTime = newTime;
		}
		h.setXVel(0);
		h.setYVel(0);
	}
	
}