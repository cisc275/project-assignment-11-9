//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.util.*;

/*
 * Public abstract class Animal defines general behaviors and attributes for movable objects.
 */
public abstract class Animal extends GameObject {

	private double xVelocity;
	private double yVelocity;
	private int speedMod;
	private Direction direction;

	public double getXVel() { return this.xVelocity; }

	public void setXVel(double xVel) { this.xVelocity = xVel; }

	public double getYVel() { return this.yVelocity; }

	public void setYVel(double yVel) { this.yVelocity = yVel; }
	
	public int getSpeedMod() { return this.speedMod; }
	
	public void setSpeedMod(int speedMod) { this.speedMod = speedMod; }
	
	public Direction getDirection() { return this.direction; }
	
	public void setDirection(Direction direction) { this.direction = direction; }

	/*
	 * public method interact.
	 * Takes GameObject as parameter and returns nothing.
	 * Processes an interaction between the Animal and the GameObject, a general ricochet.
	 */
	public void interact(GameObject g) {
		this.setXVel(-1 * this.xVelocity);
		this.setYVel(-1 * this.yVelocity);
		updateDirection();
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
	 * public method twitch.
	 * Takes int as parameter and returns nothing.
	 * Changes the velocities randomly on a random interval determined by chanceMod.
	 */
	public void twitch(int chanceMod, int seedMod) {
		if(Model.rand.nextInt(chanceMod) % chanceMod == 0) {
			xVelocity = Model.rand.nextDouble() * 2 * speedMod - speedMod;
			yVelocity = Model.rand.nextDouble() * 2 * speedMod - speedMod;
		}
	}

	/*
	 * public method updateDirection.
	 * Takes no parameters and returns nothing.
	 * Updates the direction of the animal by checking signs of velocities.
	 */
	public void updateDirection() {
		if(xVelocity < 0) {
			if(yVelocity < 0) { direction = Direction.NORTHWEST; }
			else if(yVelocity > 0) { direction = Direction.SOUTHWEST; }
			else { direction = Direction.WEST; }
		}
		else if(xVelocity > 0) {
			if(yVelocity < 0) { direction = Direction.NORTHEAST; }
			else if(yVelocity > 0) { direction = Direction.SOUTHEAST; }
			else { direction = Direction.EAST; }
		}
		else {
			if( yVelocity < 0) { direction = Direction.NORTH; }
			else { direction = Direction.SOUTH; }
		}
	}

}