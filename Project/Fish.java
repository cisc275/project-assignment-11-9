//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
/* 
 * Public class Fish contains the behaviors and attributes of Fish, the consumables in the Osprey game.
 */
public class Fish extends Animal {

	private int size;
	private final static int SIZE_OFFSET = 15;
	private final static int WIDTH_MULTIPLIER = 10;
	private final static int HEIGHT_MULTIPLIER = 5;
	private final static double VELOCITY_DIVISOR = 2;
	public final static int MAX_SIZE = 3;
	public final static int MIN_SIZE = 1;

	public Fish(double x, double y, int size) {
		setXPos(x);
		setYPos(y);
		setXWidth(WIDTH_MULTIPLIER * size + SIZE_OFFSET);
		setYWidth(HEIGHT_MULTIPLIER * size + SIZE_OFFSET);
		setXVel(Model.randomSign() * (Model.rand.nextDouble() + 1) * size / VELOCITY_DIVISOR);
		updateDirection();
		this.size = size;
	}

	public int getSize() { return size; }

	public void setSize(int size) { this.size = size; }
	
	/*
	 * public method interact.
	 * Parameters:
	 *     Osprey: o
	 * Returns: nothing
	 * Processes an interaction between the Osprey and the Fish.
	 */
	public void interact(Osprey o) {
		if(o.getXVel() + size / VELOCITY_DIVISOR < Osprey.MAX_SPEED) { o.setXVel(o.getXVel() + size / VELOCITY_DIVISOR); }
		else { o.setXVel(Osprey.MAX_SPEED); }
		o.setIsRecovering(true);
		o.rise();
	}
	
}
