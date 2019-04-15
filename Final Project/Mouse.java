//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

package Project;

/* 
 * Public class Mouse contains the behaviors and attributes of Mice, the primary consumables in the Harrier game.
 */
public class Mouse extends Animal {
	int size;
	
	public Mouse() {
		super();
		setXWidth(5);
		setYWidth(5);
		size = 1;
	}

	public int getSize() { return size; }

	public void setSize(int size) { this.size = size; }
	
	
}
