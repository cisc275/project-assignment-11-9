//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

package Project;

/* 
 * Public class Osprey contains the behaviors and attributes of Ospreys, one of the types of playable characters.
 */
public class Osprey extends Animal {
	
	public Osprey(){
		super();
		setXWidth(25);
		setYWidth(25);
		
	}
	
	/*
	 * public method interact.
	 * Takes Fish as parameter and returns nothing.
	 * Processes an interaction between the Osprey and the Fish.
	 */
	public void interact(Fish f) {
		
		this.setXVel(2*getXVel());
		
	}
	
	/*
	 * public method interact.
	 * Takes GoldenFish as parameter and returns nothing.
	 * Processes an interaction between the Osprey and the GoldenFish.
	 */
	public void interact(GoldenFish gf) {
		
		this.setXVel(3*getXVel());
		
	}
	
	/*
	 * public method interact.
	 * Takes Seaweed as parameter and returns nothing.
	 * Processes an interaction between the Osprey and the Seaweed.
	 */
	public void interact(Seaweed s) {
		
		this.setXVel(-1*getXVel());
		
	}

	/*
	 * public method dive.
	 * Takes no parameters and returns nothing.
	 * Makes the osprey dive by incrementing its yVelocity.
	 */
	public void dive() {
		
		this.setYVel(2*getYVel());
		
		
		
		
	}
	
}
