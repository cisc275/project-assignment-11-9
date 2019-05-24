//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;

/* 
 * Public class Seaweed contains the behaviors and attributes of Seaweed, the obstacles in the Osprey game.
 */
public class Seaweed extends GameObject {
	
	boolean hasBeenHit = false;
	private final static int WIDTH = 35;
	private final static int HEIGHT = 60;
	private final static int PENALTY = 1;
	
	Seaweed(double x, double y){
		setXPos(x);
		setYPos(y);
		setXWidth(WIDTH);
		setYWidth(HEIGHT);
	}
	
	/*
	 * public method interact.
	 * Takes Osprey as parameter and returns nothing.
	 * Processes an interaction between the Osprey and the Seaweed if it has not already been hit.
	 */
	public void interact(Osprey o) {
		if (!hasBeenHit && !o.getIsRecovering()) {
			hasBeenHit = true;
			o.setXVel(o.getXVel() - PENALTY);
			o.setIsRecovering(true);
			o.rise();
		}
	}

}
