//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;

/* 
 * Public class GoldenFish contains the behaviors and attributes of Golden Fish, the special power-up in the Osprey game.
 */
public class GoldenFish extends Fish {
	
	String answer = "1";
	String correct = "2";
	
	public GoldenFish(double x, double y) {
		super(x, y, 0);
	}
	
	/*
	 * public method interact.
	 * Takes Osprey as parameter and returns nothing.
	 * Processes an interaction between the Osprey and the GoldenFish if the player correctly answers the triggered quiz.
	 */
	public void interact(Osprey o) {
		Quiz quiz = new Quiz(this);
		if (answer.equals(correct)) {
			if(o.getXVel() + 5 < Osprey.MAX_SPEED) { o.setXVel(o.getXVel() + 5); }
			else { o.setXVel(Osprey.MAX_SPEED); }
		}
		o.setIsRecovering(true);
		o.rise();
	}
	
}
