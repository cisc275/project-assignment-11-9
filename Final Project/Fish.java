//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

package Project;
/* 
 * Public class Fish contains the behaviors and attributes of Fish, the consumables in the Osprey game.
 */
public class Fish extends Animal {

	private int size;

	public Fish(double x, double y, int size) {
		setXPos(x);
		setYPos(y);
		setXWidth(15 * size);
		setYWidth(15 * size);
		setXVel(0);
		setYVel(0);
		setSpeedMod(3);
		updateDirection();
		this.size = size;
	}

	public int getSize() { return size; }

	public void setSize(int size) { this.size = size; }

	/*
	 * public method interact.
	 * Takes Osprey as parameter and returns nothing.
	 * Processes an interaction between the Osprey and the Fish.
	 */
	public void interact(Osprey o) {
		if (o.getXVel() + size < OspreyModel.MAX_SPEED) {
			o.setXVel(o.getXVel() + size);
		} else {
			o.setXVel(OspreyModel.MAX_SPEED);
		}
	}
	
}