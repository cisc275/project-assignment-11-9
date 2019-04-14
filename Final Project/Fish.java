//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

package Project;

/* 
 * public class Fish contains the behaviors and attributes of Fish.
 */
public class Fish extends Animal {
	
	int size;
	
	/*
	 * public method interact.
	 * Takes Osprey object and returns nothing.
	 * Processes an interaction with the Osprey.
	 */
	public void interact(Osprey o) {
	}

	/*
	 * public method move.
	 * Takes no parameters and returns nothing.
	 * Updates the position, velocity, and direction of the animal.
	 */
	@Override
	public void move() {
	}
	
	public int getSize() { return size; }
	
	public void setSize(int size) { this.size = size; }
}
