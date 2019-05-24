//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
/* 
 * Public class Osprey contains the behaviors and attributes of Ospreys, one of the types of playable characters.
 */
public class Osprey extends Animal {
	
	private boolean isRecovering;
	public int gameTimer;
	public final static double MIN_HEIGHT = 0;
	public final static double MAX_HEIGHT = TitleView.FRAME_HEIGHT - 2 * OspreyView.Y_OFFSET;
	public final static double MIN_SPEED = 3;
	public final static double MAX_SPEED = 40;
	public final static double FLYING_SPEED = 15;
	public final static double START_SPEED = 5;
	private final static int WIDTH = 75;
	private final static int HEIGHT = 35;
	
	public Osprey(){
		setXPos(0);
		setYPos(0);
		setXWidth(WIDTH);
		setYWidth(HEIGHT);
		setXVel(START_SPEED);
		setYVel(0);
		isRecovering = false;
	}
	
	public boolean getIsRecovering() { return isRecovering; }
	
	public void setIsRecovering(boolean isRecovering) { this.isRecovering = isRecovering; }
	
	/*
	 * public method move.
	 * Parameters: none
	 * Returns: nothing
	 * Makes the Osprey move, but with applied bounds.
	 */
	@Override
	public void move() {
		setXPos(getXPos() + getXVel());
		if(getYPos() + getYVel() < MIN_HEIGHT || getYPos() + getYVel() > MAX_HEIGHT) { setYVel(0); }
		else { setYPos(getYPos() + getYVel()); }
	}

	/*
	 * public method dive.
	 * Parameters: none
	 * Returns: nothing
	 * Makes the Osprey dive by changing its yVelocity.
	 */
	public void dive() {
		setYVel(FLYING_SPEED);
	}
	
	/*
	 * public method rise.
	 * Parameters: none
	 * Returns: nothing
	 * Makes the Osprey rise by changing its yVelocity.
	 */
	public void rise() {
		setYVel(-FLYING_SPEED);
	}

}
