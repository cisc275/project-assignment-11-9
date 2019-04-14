//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

package Project;

/*
 * Public abstract class Animal defines general behaviors and attributes for Animals.
 */
public abstract class Animal extends GameObject {
	
	int xVelocity;
	int yVelocity;
	Direction direction;
	
	/*
	 * public abstract method move.
	 * Takes no parameters and returns nothing.
	 * Updates the position, velocity, and direction of the animal.
	 */
	public abstract void move();
	
	public int getXVel() { return xVelocity; }
	
	public void setXVel(int xVel) { xVelocity = xVel; }
	
	public int getYVel() { return yVelocity; }
	
	public void setYVel(int yVel) { yVelocity = yVel; }
}
