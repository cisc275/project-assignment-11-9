//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.util.*;

/*
 * Public abstract class Animal defines general behaviors and attributes for movable objects.
 */
public abstract class Animal extends GameObject {

	private double xVelocity;
	private double yVelocity;
	private double velAngle;
	private Direction direction;
	private double speedMod;
	private int chanceMod;
	private int animationCounter;
	private int animationDelay;
	private final static int DELAY = 6;
	private final static int NUM_FRAMES = 4;
	private final static double EAST_UPPER_BOUND = 22.5;
	private final static double EAST_LOWER_BOUND = 337.5;
	private final static double NORTHEAST_UPPER_BOUND = 67.5;
	private final static double NORTH_UPPER_BOUND = 112.5;
	private final static double NORTHWEST_UPPER_BOUND = 157.5;
	private final static double WEST_UPPER_BOUND = 202.5;
	private final static double SOUTHWEST_UPPER_BOUND = 247.5;
	private final static double SOUTH_UPPER_BOUND = 292.5;

	public double getXVel() { return this.xVelocity; }

	public void setXVel(double xVel) { this.xVelocity = xVel; }

	public double getYVel() { return this.yVelocity; }

	public void setYVel(double yVel) { this.yVelocity = yVel; }
	
	public double getAngle() { return this.velAngle; }
	
	public void setAngle(double velAngle) { this.velAngle = velAngle; }
	
	public Direction getDirection() { return this.direction; }
	
	public void setDirection(Direction direction) { this.direction = direction; }
	
	public double getSpeedMod() { return this.speedMod; }
	
	public void setSpeedMod(double speedMod) { this.speedMod = speedMod; }
	
	public int getChanceMod() { return this.chanceMod; }
	
	public void setChanceMod(int chanceMod) { this.chanceMod = chanceMod; }
	
	public int getAnimation() { return this.animationCounter; }
	
	public void setAnimation(int animationCounter) { this.animationCounter = animationCounter; }
	
	/*
	 * public method incrementAnimation.
	 * Takes no parameters, and returns nothing.
	 * Increments the animationCounter.
	 */
	public void incrementAnimation() {
		animationDelay = (animationDelay + 1) % DELAY;
		if(animationDelay == 0) { animationCounter = (animationCounter + 1) % NUM_FRAMES; }
	}

	/*
	 * public method move.
	 * Takes no parameters and returns nothing.
	 * Increments the positions by their respective velocity.
	 */
	public void move() {
		setXPos(getXPos() + xVelocity);
		setYPos(getYPos() + yVelocity);
	}
	
	/*
	 * public method moveBounded.
	 * Takes two doubles and int as parameters and returns nothing.
	 * Increments the positions by their respective velocity if within bounds, otherwise sends animal into bounds.
	 */
	public void moveBounded(double xBound, double yBound, int velMultiplier) {
		double xFuture = getXPos() + xVelocity;
		if (xFuture < xBound && xFuture > -xBound) { setXPos(getXPos() + xVelocity); }
		else {
			setXVel(velMultiplier * getXVel());
			if(velMultiplier != 0) { updateDirection(); }
		}
		double yFuture = getYPos() + yVelocity;
		if (yFuture < yBound && yFuture > -yBound) { setYPos(getYPos() + yVelocity); }
		else {
			setYVel(velMultiplier * getYVel());
			if(velMultiplier != 0) { updateDirection(); }
		}
	}
	
	/*
	 * public method twitch.
	 * Takes no parameters and returns nothing.
	 * Changes the velocities randomly on a random interval determined by chanceMod.
	 */
	public void twitch() {
		if(Model.rand.nextInt(chanceMod) == 0) {
			xVelocity = Model.rand.nextDouble() * 2 * speedMod - speedMod;
			yVelocity = Model.rand.nextDouble() * 2 * speedMod - speedMod;
		}
		updateDirection();
	}
	
	/*
	 * public method turnCCW.
	 * Takes double as parameter and returns nothing.
	 * Changes the velocities to allow the Animal to turn counter-clockwise by the given angle.
	 */
	public void turnCCW (double angle) {
		double newXVel = getXVel() * Math.cos(angle) + getYVel() * Math.sin(angle);
		double newYVel = getYVel() * Math.cos(angle) - getXVel() * Math.sin(angle);
		xVelocity = newXVel;
		yVelocity = newYVel;
		updateDirection();
	}
	
	/*
	 * public method interact.
	 * Takes GameObject as parameter and returns nothing.
	 * Processes an interaction between the Animal and the GameObject, a general ricochet.
	 */
	public void interact(GameObject g) {
		xVelocity *= -1;
		yVelocity *= -1;
		updateDirection();
	}
	
	/*
	 * public method calcSpeed.
	 * Takes no parameters and returns double.
	 * Calculates the speed of the Animal.
	 */
	public double calcSpeed() { return Math.sqrt(xVelocity * xVelocity + yVelocity * yVelocity); }
	
	public void updateAngle() {
		velAngle = Math.atan(-getYVel()/getXVel()); // negative because up from a viewer's perspecitve is in the negative y direction.
		if(getXVel() < 0) { velAngle += Math.PI; }
		else if (velAngle < 0) { velAngle += 2*Math.PI; }
	}

	/*
	 * private method updateDirection.
	 * Takes no parameters and returns nothing.
	 * Updates the direction of the animal by checking angle of velocity.
	 */
	public void updateDirection() {
		updateAngle();
		double degrees = velAngle * 180 / Math.PI;
		if(degrees < EAST_UPPER_BOUND || degrees > EAST_LOWER_BOUND) { direction = Direction.EAST; }
		else if(degrees < NORTHEAST_UPPER_BOUND) { direction = Direction.NORTHEAST; }
		else if(degrees < NORTH_UPPER_BOUND) { direction = Direction.NORTH; }
		else if(degrees < NORTHWEST_UPPER_BOUND) { direction = Direction.NORTHWEST; }
		else if(degrees < WEST_UPPER_BOUND) { direction = Direction.WEST; }
		else if(degrees < SOUTHWEST_UPPER_BOUND) { direction = Direction.SOUTHWEST; }
		else if(degrees < SOUTH_UPPER_BOUND) { direction = Direction.SOUTH; }
		else { direction = Direction.SOUTHEAST; }
	}

}
