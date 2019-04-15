//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

package Project;

/* 
 * Public class Fox contains the behaviors and attributes of Foxes, the predators in the Harrier game.
 */
public class Fox extends Animal {
	int size;
	
	public Fox() {
		super();
		setXWidth(5);
		setYWidth(5);
		size = 1;
	}

	public int getSize() { return size; }

	public void setSize(int size) { this.size = size; }
	
	
}
	/*
	 * public method chase.
	 * Takes Harrier object and returns nothing.
	 * Makes the Fox chase the Harrier by incrementing the Fox's velocities to direct at the Harrier's position.
	 */
	public void chase(Harrier h) {
		
	}
	
}
