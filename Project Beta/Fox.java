//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;

/* 
 * Public class Fox contains the behaviors and attributes of Foxes, the predators in the Harrier game.
 */
public class Fox extends Animal {
	
	private final static double CHASE_RADIUS = 500;
	
	public Fox(double x, double y) {
		setXPos(x);
		setYPos(y);
		setXWidth(35);
		setYWidth(35);
		setSpeedMod(3.5);
		setChanceMod(50);
	}
	
	/*
	 * public method interact.
	 * Takes Harrier as parameter and returns nothing.
	 * Processes an interaction between the Harrier and the Fox.
	 */
	public void interact(Harrier h) {
		h.setScore(h.getScore() - 30);
		h.setVision(h.getVision() - 75);
		h.die();
	}
	
	/*
	 * public method chase.
	 * Takes Harrier object and returns nothing.
	 * Makes the Fox chase the Harrier by incrementing the Fox's velocities to direct at the Harrier's position.
	 */
	public void chase(Harrier h) {
		double dx = h.getXPos() - this.getXPos();
		double dy = h.getYPos() - this.getYPos();
		double chaseAngle = Math.atan(dy/dx);
		if(dx < 0) { chaseAngle += Math.PI; }
		setXVel( getSpeedMod() * Math.cos(chaseAngle));
		setYVel( getSpeedMod() * Math.sin(chaseAngle));
		updateDirection();
	}
	
	/*
	 * public method roam.
	 * Takes no parameters and returns nothing.
	 * Makes the fox possibly change direction or chase the Harrier, and move.
	 */
	public void roam(Harrier h, double xBound, double yBound, int velMultiplier) {
		double dx = h.getXPos() - this.getXPos();
		double dy = h.getYPos() - this.getYPos();
		double dist = Math.sqrt(dx * dx + dy * dy);
		double hx = h.getXPos();
		double hy = h.getYPos();
		double hdist = Math.sqrt(hx * hx + hy * hy);
		if(dist < CHASE_RADIUS && hdist > HarrierModel.EXCLUSION_RADIUS) { chase(h); }
		else { twitch(); }
		moveBounded(xBound, yBound, velMultiplier);
	}
	
}
