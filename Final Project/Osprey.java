//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;

/* 
 * Public class Osprey contains the behaviors and attributes of Ospreys, one of the types of playable characters.
 */
public class Osprey extends Animal {
	
	private final static double MIN_HEIGHT = 0;
	private final static double FLYING_SPEED = 25;
	
	public Osprey(){
		setXPos(0);
		setYPos(0);
		setXWidth(100);
		setYWidth(50);
		setXVel(10);
		setYVel(0);
		updateDirection();
	}
	
	@Override
	public void move() {
		setXPos(getXPos() + getXVel());
		if(getYPos() + getYVel() < MIN_HEIGHT || getYPos() + getYVel() > TitleView.FRAME_HEIGHT - 2 * OspreyView.Y_OFFSET) { setYVel(0); updateDirection(); }
		else { setYPos(getYPos() + getYVel()); }
	}

	/*
	 * public method dive.
	 * Takes no parameters and returns nothing.
	 * Makes the Osprey dive by incrementing its yVelocity.
	 */
	public void dive() {
		setYVel(FLYING_SPEED);
		updateDirection();
	}
	
	/*
	 * public method rise.
	 * Takes no parameters and returns nothing.
	 * Makes the Osprey rise by decrementing its yVelocity.
	 */
	public void rise() {
		setYVel(-FLYING_SPEED);
		updateDirection();
	}

}
