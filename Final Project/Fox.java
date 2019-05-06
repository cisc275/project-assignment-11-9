//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

package Project;
/* 
 * Public class Fox contains the behaviors and attributes of Foxes, the predators in the Harrier game.
 */
public class Fox extends Animal {
	
	public Fox(double x, double y) {
		setXPos(x);
		setYPos(y);
		setXWidth(75);
		setYWidth(75);
		setXVel(5);
		setYVel(3);
		setSpeedMod(5);
		updateDirection();
	}
	
	/*
	 * public method interact.
	 * Takes Harrier as parameter and returns nothing.
	 * Processes an interaction between the Harrier and the Fox.
	 */
	public void interact(Harrier h) {
		h.setScore(h.getScore() - 30);
		h.die();
		if (h.getVision() - 75 > HarrierModel.MIN_VISION) {
			h.setVision(h.getVision() - 75);
		} else {
			h.setVision(HarrierModel.MIN_VISION);
		}
	}
	
	/*
	 * public method chase.
	 * Takes Harrier object and returns nothing.
	 * Makes the Fox chase the Harrier by incrementing the Fox's velocities to direct at the Harrier's position.
	 */
	public void chase(Harrier h) {
		if(h.getXPos() > this.getXPos()) { setXVel(5); }
		else if(h.getXPos() < this.getXPos()) { setXVel(-5); }
		else { setXVel(0); }
		if(h.getYPos() > this.getYPos()) { setYVel(5); }
		else if(h.getYPos() < this.getYPos()) { setYVel(-5); }
		else { setYVel(0); }
	}
	
}