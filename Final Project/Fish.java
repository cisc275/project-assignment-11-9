//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

package Project;

/* 
 * Public class Fish contains the behaviors and attributes of Fish, the consumables in the Osprey game.
 */
public class Fish extends Animal {

	int size;

	public Fish() {
		super();
		size = 1;
	}

	public int getSize() { return size; }

	public void setSize(int size) { this.size = size; }
	
}
