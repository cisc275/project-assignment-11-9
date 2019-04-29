
//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
/* 
* Public interface HarrierAble requires that the GameObject be able to interact with a Harrier.
*/
public interface HarrierAble {
	
	/*
	 * public method interact.
	 * Takes Harrier as parameter and returns nothing.
	 * Processes an interaction between the Harrier and the GameObject.
	 */
	public void interact(Harrier h);
	
	public double getXPos();
	
	public double getYPos();
	
	public double getXWidth();
	
	public double getYWidth();
	
	public boolean isAnimal();
	public boolean isFox();
	public boolean isGoldenMouse();
	public boolean isHarrier();
	public boolean isMouse();
	public boolean isTree();
	public boolean isTwig();
}
