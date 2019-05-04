//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
/* 
 * Public class Seaweed contains the behaviors and attributes of Seaweed, the obstacles in the Osprey game.
 */
public class Seaweed extends GameObject implements OspreyAble {
	
	boolean hasBeenHit = false;
	
	public Seaweed() {
		double randPositionX= Math.random() * ( 1500 - 0 ) + 0;
		double randPositionY= Math.random() * ( 1500 - 0 ) + 0;
		setXPos(randPositionX);
		setYPos(randPositionY);
		setXWidth(50);
		setYWidth(80);
	}
	
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
			if (o.getXVel() - 2 > 4)
				o.setXVel(o.getXVel() - 2);
			else
				o.setXVel(4);
			hasBeenHit = true;
		}
	}
	
	public boolean isSeaweed() {
		return true;
	}
}
