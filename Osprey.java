//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;

/* 
 * Public class Osprey contains the behaviors and attributes of Ospreys, one of the types of playable characters.
 */
public class Osprey extends Animal {
	
	private boolean isRecovering;
	public final static double MIN_HEIGHT = 0;
	public final static double MAX_HEIGHT = TitleView.FRAME_HEIGHT - 2 * OspreyView.Y_OFFSET;
	public final static double MIN_SPEED = 4;
	public final static double MAX_SPEED = 40;
	public final static double FLYING_SPEED = 15;
	public final static double START_SPEED = 5;
	public int gameTimer;
	
	public Osprey(){
		setXPos(0);
		setYPos(0);
		setXWidth(75);
		setYWidth(35);
		setXVel(5);
		setYVel(0);
		gameTimer = 0;
		isRecovering = false;
	}
	
	public boolean getIsRecovering() { return isRecovering; }
	
	public void setIsRecovering(boolean isRecovering) { this.isRecovering = isRecovering; }
	
	/*
	 * public method move.
	 * Takes no parameters and returns nothing.
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
	 * Takes no parameters and returns nothing.
	 * Makes the Osprey dive by incrementing its yVelocity.
	 */
	public void dive() {
		setYVel(FLYING_SPEED);
	}
	
	/*
	 * public method rise.
	 * Takes no parameters and returns nothing.
	 * Makes the Osprey rise by decrementing its yVelocity.
	 */
	public void rise() {
		setYVel(-FLYING_SPEED);
	}
	

}
