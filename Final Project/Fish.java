//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
/* 
 * Public class Fish contains the behaviors and attributes of Fish, the consumables in the Osprey game.
 */
public class Fish extends Animal implements OspreyAble {

	private int size;
	
	public Fish(int size) {
		double randPositionX= Math.random() * ( 500 - 0 ) + 0;
		double randPositionY= Math.random() * ( 500 - 0 ) + 0;
		setXPos(randPositionX);
		setYPos(randPositionY);
		setXWidth(20 * size);
		setYWidth(20 * size);
		setXVel(2);
		setYVel(0);
		setSpeedMod(3);
		updateDirection();
		this.size = size;	
	}
	
	public Fish(double x, double y, int size) {
		setXPos(x);
		setYPos(y);
		setXWidth(20 * size);
		setYWidth(20 * size);
		setXVel(2);
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
		o.setXVel(o.getXVel() + size);
	}
	
	@Override
	public boolean isFish() {
		return true;
	}
}