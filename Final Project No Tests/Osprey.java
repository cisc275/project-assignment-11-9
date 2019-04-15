//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

package Project;

/* 
 * Public class Osprey contains the behaviors and attributes of Ospreys, one of the types of playable characters.
 */
public class Osprey extends Animal {
	
	public Osprey(){
		setXPos(0);
		setYPos(0);
		setXWidth(100);
		setYWidth(50);
		setXVel(10);
		setYVel(0);
		updateDirection();
	}

	/*
	 * public method dive.
	 * Takes no parameters and returns nothing.
	 * Makes the Osprey dive by incrementing its yVelocity.
	 */
	public void dive() {
		setYVel(getYVel() + 5);
		updateDirection();
	}
	
	/*
	 * public method rise.
	 * Takes no parameters and returns nothing.
	 * Makes the Osprey rise by decrementing its yVelocity.
	 */
	public void rise() {
		setYVel(getYVel() - 5);
		updateDirection();
	}
	
	public String toString() { return "Osprey @ (" + getXPos() + "," + getYPos() + ")"; } 
	
}
