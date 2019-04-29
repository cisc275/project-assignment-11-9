//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
/* 
 * Public class Twig contains the behaviors and attributes of Twigs, the primary consumables in the Harrier game.
 */
public class Twig extends GameObject implements HarrierAble {
	
	public Twig(double x, double y) {
		setXPos(x);
		setYPos(y);
		setXWidth(15);
		setYWidth(30);
	}
	
	/*
	 * public method interact.
	 * Takes Harrier as parameter and returns nothing.
	 * Processes an interaction between the Harrier and the Twig.
	 */
	public void interact(Harrier h) {
		h.setScore(h.getScore() + 20);
	}
	
	@Override
	public boolean isTwig() {
		return true;
	}
	
}
