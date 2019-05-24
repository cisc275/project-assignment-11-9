//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;

/* 
 * Public class Fox contains the behaviors and attributes of Foxes, the predators in the Harrier game.
 */
public class Fox extends Animal {
	
	private final static double CHASE_RADIUS = 500;
	private final static double SIZE = 35;
	private final static double SPEED = 3.5;
	private final static int CHANCE = 50;
	private final static int SCORE_HIT = 30;
	private final static int VISION_HIT = 75;
	
	public Fox(double x, double y) {
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
	 * Processes an interaction between the Harrier and the Fox.
	 */
	public void interact(Harrier h) {
		h.setScore(h.getScore() - SCORE_HIT);
		h.setVision(h.getVision() - VISION_HIT);
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
		double dist = calcDist(h);
		double hdist = h.calcDist();
		if(dist < CHASE_RADIUS && hdist > HarrierModel.EXCLUSION_RADIUS) { chase(h); }
		else { twitch(); }
		moveBounded(xBound, yBound, velMultiplier);
	}
	
}
