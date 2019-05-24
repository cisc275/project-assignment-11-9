//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;

/* 
 * Public class Harrier contains the behaviors and attributes of Harriers, one of the types of playable characters.
 */
public class Harrier extends Animal {

	private int score;
	private double visionRadius;
	public static int INITIAL_VISION = 300;
	private static int STARTPOS = 0;
	private static int STARTVEL = 0;
	private static int SIZE = 40;
	public final static double MIN_VISION = 0;
	public final static double MAX_VISION = 2 * INITIAL_VISION;
	private final static double MAX_SPEED = 5;
	private final static double ACCELERATION = MAX_SPEED / 3;
	private final static double TURN_ANGLE = Math.PI / 12;

	public Harrier() {
		setXPos(STARTPOS);
		setYPos(STARTPOS);
		setXWidth(SIZE);
		setYWidth(SIZE);
		setXVel(STARTVEL);
		setYVel(STARTVEL);
		updateDirection();
		score = 0;
		visionRadius = INITIAL_VISION;
	}
	
	public int getScore() { return this.score; }

	public void setScore(int score) { this.score = score; }

	public double getVision() { return this.visionRadius; }

	public void setVision(double visionRadius) { this.visionRadius = visionRadius; }
	
	/*
	 * public method goNorth.
	 * Parameters: none
	 * Returns: nothing
	 * Makes the Harrier turn north and accelerate northwards.
	 */
	public void goNorth() {
		if(calcSpeed() < MAX_SPEED) { setYVel(getYVel() - ACCELERATION); }
		if(Math.abs(getAngle() - Math.PI / 2) >= TURN_ANGLE) {
			if(getAngle() < Math.PI / 2 || getAngle() > 3 * Math.PI / 2) { turnCCW(TURN_ANGLE); }
			else { turnCCW(-TURN_ANGLE); }
		} else { setXVel(0); setYVel(-calcSpeed()); }
		updateDirection();
	}
	
	/*
	 * public method goSouth.
	 * Parameters: none
	 * Returns: nothing
	 * Makes the Harrier turn south and accelerate southwards.
	 */
	public void goSouth() {
		if(calcSpeed() < MAX_SPEED) { setYVel(getYVel() + ACCELERATION); }
		if(Math.abs(getAngle() - 3 * Math.PI / 2) >= TURN_ANGLE) {
			if(getAngle() < Math.PI / 2 || getAngle() > 3 * Math.PI / 2) { turnCCW(-TURN_ANGLE); }
			else { turnCCW(TURN_ANGLE); }
		}
		else { setXVel(0); setYVel(calcSpeed()); }
		updateDirection();
	}
	
	/*
	 * public method goEast.
	 * Parameters: none
	 * Returns: nothing
	 * Makes the Harrier turn east and accelerate eastwards.
	 */
	public void goEast() {
		if(calcSpeed() < MAX_SPEED) { setXVel(getXVel() + ACCELERATION); }
		if(Math.abs(getAngle()) >= TURN_ANGLE) {
			if(getAngle() < Math.PI) { turnCCW(-TURN_ANGLE); }
			else { turnCCW(TURN_ANGLE); }
		}
		else { setXVel(calcSpeed()); setYVel(0); }
		updateDirection();
	}
	
	/*
	 * public method goWest.
	 * Parameters: none
	 * Returns: nothing
	 * Makes the Harrier turn west and accelerate westwards.
	 */
	public void goWest() {
		if(calcSpeed() < MAX_SPEED) { setXVel(getXVel() - ACCELERATION); }
		if(Math.abs(getAngle() - Math.PI) >= TURN_ANGLE) {
			if(getAngle() < Math.PI) { turnCCW(TURN_ANGLE); }
			else { turnCCW(-TURN_ANGLE); }
		}
		else { setXVel(-calcSpeed()); setYVel(0); }
		updateDirection();
	}

	/*
	 * public method die.
	 * Parameters: none
	 * Returns: nothing
	 * Resets the Harrier to its spawn point with 0 velocity.
	 */
	public void die() {
		setXPos(STARTPOS);
		setYPos(STARTPOS);
		setXVel(STARTVEL);
		setYVel(STARTVEL);
		updateDirection();
	}

}