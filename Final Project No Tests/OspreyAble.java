//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

package Project;

/* 
 * Public interface OspreyAble requires that the GameObject be able to interact with an Osprey.
 */
public interface OspreyAble {

	/*
	 * public method interact.
	 * Takes Osprey as parameter and returns nothing.
	 * Processes an interaction between the Osprey and the GameObject.
	 */
	public void interact(Osprey o);

	public double getXPos();

	public double getYPos();

	public double getXWidth();

	public double getYWidth();
}
