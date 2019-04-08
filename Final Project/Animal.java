//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
/*
 * Public abstact class Animal defines general behaviors and attributes for Animals.
 */
public abstract class Animal extends GameObject {

	/*
	 * public abstract method interact.
	 * Takes no parameters and returns nothing.
	 * Processes an interaction with another GameObject.
	 */
	@Override
	public abstract void interact();
	
	/*
	 * public abstract method move.
	 * Takes no parameters and returns nothing.
	 * Updates the position, velocity, and direction of the animal.
	 */
	public abstract void move();
}
