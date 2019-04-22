//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
/* 
 * Public class Osprey contains the behaviors and attributes of Ospreys, one of the types of playable characters.
 */
public class Osprey extends Animal {
	static int STABLEYVEL = 0;
	static int DIVINGYVEL = 5;
	static int RISINGYVEL = -5;
	boolean isRising;
	boolean isBouncing;
	double bouncingVel;
	boolean justBounced;
	double maxHeight;
	
	public Osprey(){
		setXPos(0);
		setYPos(0);
		setXWidth(100);
		setYWidth(50);
		setXVel(10);
		setYVel(0);
		updateDirection();
		maxHeight = 0;
		bouncingVel = 0;
		justBounced = false;
	}

	@Override
	public void move() {
		setXPos(getXPos() + getXVel());
		if (isRising) {
			if (getYPos() + getYVel() >= maxHeight) {
				setYPos(getYPos() + getYVel());
			} else {
				setYPos(maxHeight);
				isRising = false;
				setYVel(0);
			}
		} else {
			setYPos(getYPos() + getYVel());
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
		isRising = true;
		setYVel(RISINGYVEL);
		updateDirection();
	}
	
	
	public boolean isRising() {
		return isRising;
	}
	
	public String toString() { return "Osprey @ (" + (int)getXPos() + "," + (int)getYPos() + ")"; } 
	
}
