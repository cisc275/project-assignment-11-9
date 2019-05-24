//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;

/* 
 * Public class GoldenMouse contains the behaviors and attributes of Golden Mice, the special power-up in the Harrier game.
 */
public class GoldenMouse extends Mouse {
	
	private boolean tutorial = false;
	private boolean correct = false;
	private final static int GOLD_SPEED = 5;
	private final static int POINT_BOOST = 100;
	private final static int VISION_BOOST = 250;
	
	public boolean getTutorial() { return tutorial; }
	public boolean getCorrect() { return correct; }
	public void setCorrect(boolean correct) { this.correct = correct; }
	
	public GoldenMouse(double x, double y) {
		super(x, y);
		setSpeedMod(GOLD_SPEED);
	}
	public GoldenMouse(double x, double y, boolean tutorial) {
		super(x, y);
		setSpeedMod(GOLD_SPEED);
		this.tutorial = tutorial;
	}
	
	/*
	 * public method interact.
	 * Parameters:
	 *     Harrier: h
	 * Returns: nothing
	 * Triggers a quiz, and if the user gets the quiz question right, boosts the Harrier.
	 */
	public void interact(Harrier h) {
		Quiz quiz = new Quiz(this);
		if (correct) {
			h.setScore(h.getScore() + POINT_BOOST);
			if(h.getVision() + VISION_BOOST < Harrier.MAX_VISION) { h.setVision(h.getVision() + VISION_BOOST); }
			else { h.setVision(Harrier.MAX_VISION); }
		}
	}
	
}