//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;

/* 
 * Public class GoldenFish contains the behaviors and attributes of Golden Fish, the special power-up in the Osprey game.
 */
public class GoldenFish extends Fish {
	
	private boolean tutorial = false;
	private boolean correct = false;
	private final static int BOOST = 5;
	
	public boolean getTutorial() { return tutorial; }
	public boolean getCorrect() { return correct; }
	public void setCorrect(boolean correct) { this.correct = correct; }
	
	public GoldenFish(double x, double y) {
		super(x, y, 0);
	}
	
	public GoldenFish(double x, double y, boolean tutorial) {
		super(x, y, 0);
		this.tutorial = tutorial;
	}
	
	/*
	 * public method interact.
	 * Parameters:
	 *     Osprey: o
	 * Returns: nothing
	 * Triggers a quiz, and if the user gets the quiz question right, boosts the Osprey.
	 */
	public void interact(Osprey o) {
		Quiz quiz = new Quiz(this);
		if (correct) {
			if(o.getXVel() + BOOST < Osprey.MAX_SPEED) { o.setXVel(o.getXVel() + BOOST); }
			else { o.setXVel(Osprey.MAX_SPEED); }
		}
		o.setIsRecovering(true);
		o.rise();
	}
	
}