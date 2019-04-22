//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
/* 
 * Public class Osprey contains the behaviors and attributes of Ospreys, one of the types of playable characters.
 */
public class Osprey extends Animal {
	static int STABLEYVEL = 0;
	static int DIVINGYVEL = 5;
	static int RISINGYVEL = -5;
	boolean isBouncing;
	double bouncingVel;
	boolean justBounced;
	
	public Osprey(){
		setXPos(0);
		setYPos(0);
		setXWidth(100);
		setYWidth(50);
		setXVel(10);
		setYVel(0);
		updateDirection();
		bouncingVel = 0;
		justBounced = false;
	}

	@Override
	public void move() {
		if (!isBouncing) {
			super.move();
		} else {
			if (justBounced) {
				bouncingVel = -getXVel()*2;
				justBounced = false;
			}
			setYPos(getYPos() + getYVel());
			setXPos(getXPos() + bouncingVel);
			if (bouncingVel + 2 < getXVel()) {
				bouncingVel += 2;
			} else {
				isBouncing = false;
			}
		}
	}
	/*
	 * public method dive.
	 * Takes no parameters and returns nothing.
	 * Makes the Osprey dive by incrementing its yVelocity.
	 */
	public void dive() {
		setYVel(DIVINGYVEL);
		updateDirection();
	}
	
	/*
	 * public method rise.
	 * Takes no parameters and returns nothing.
	 * Makes the Osprey rise by decrementing its yVelocity.
	 */
	public void rise() {
		setYVel(RISINGYVEL);
		updateDirection();
	}
	
	public void bounce() {
		isBouncing = true;
		justBounced = true;
		rise();
	}
	public String toString() { return "Osprey @ (" + (int)getXPos() + "," + (int)getYPos() + ")"; } 
	
}
