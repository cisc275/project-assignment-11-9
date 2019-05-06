//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
/* 
 * Public class Seaweed contains the behaviors and attributes of Seaweed, the obstacles in the Osprey game.
 */
public class Seaweed extends GameObject {
	
	boolean hasBeenHit = false;
	
	Seaweed(double x, double y){
		setXPos(x);
		setYPos(y);
		setXWidth(50);
		setYWidth(80);
	}
	
	/*
	 * public method interact.
	 * Takes Osprey as parameter and returns nothing.
	 * Processes an interaction between the Osprey and the Seaweed.
	 */
	public void interact(Osprey o) {
		if (!hasBeenHit) {
			hasBeenHit = true;
			if (o.getXVel() - 2 > OspreyModel.MIN_SPEED) {
				o.setXVel(o.getXVel() - 2);
			} else {
				o.setXVel(OspreyModel.MIN_SPEED);
			}
		}
	}

}
