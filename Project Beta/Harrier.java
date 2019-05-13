//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;

/* 
 * Public class Harrier contains the behaviors and attributes of Harriers, one of the types of playable characters.
 */
public class Harrier extends Animal {

	private int score;
	private double visionRadius;
	private static int INITIAL_VISION = 250;
	private static int STARTPOS = 0;
	private static int STARTVEL = 0;
	private static int SIZE = 60;
	public final static double MIN_VISION = 0;
	public final static double MAX_VISION = 2 * INITIAL_VISION;
	private final static double MAX_SPEED = 5;
	private final static double ACCELERATION = MAX_SPEED / 5;
	private final static double TURN_ANGLE = Math.PI / 50;

	public Harrier() {
		setXPos(STARTPOS);
		setYPos(STARTPOS);
		setXWidth(SIZE);
		setYWidth(SIZE);
		setXVel(STARTVEL);
		setYVel(STARTVEL);
		score = 0;
		visionRadius = INITIAL_VISION;
	}
	
	public int getScore() { return this.score; }

	public void setScore(int score) { this.score = score; }

	public double getVision() { return this.visionRadius; }

	public void setVision(double visionRadius) { this.visionRadius = visionRadius; }
	
	/*
	 * public method goNorth.
	 * Takes no parameters and returns nothing.
	 * Makes the Harrier turn to the northern direction and accelerate northwards.
	 */
	public void goNorth() {
		if(calcSpeed() < MAX_SPEED) { setYVel(getYVel() - ACCELERATION); }
		if(Math.abs(getAngle() - Math.PI / 2) >= TURN_ANGLE) {
			if(getAngle() < Math.PI / 2 || getAngle() > 3 * Math.PI / 2) { turnCCW(TURN_ANGLE); }
			else { turnCCW(-TURN_ANGLE); }
		}
		else { setXVel(0); setYVel(-calcSpeed()); }
	}
	
	/*
	 * public method goSouth.
	 * Takes no parameters and returns nothing.
	 * Makes the Harrier turn to the southern direction and accelerate southwards.
	 */
	public void goSouth() {
		if(calcSpeed() < MAX_SPEED) { setYVel(getYVel() + ACCELERATION); }
		if(Math.abs(getAngle() - 3 * Math.PI / 2) >= TURN_ANGLE) {
			if(getAngle() < Math.PI / 2 || getAngle() > 3 * Math.PI / 2) { turnCCW(-TURN_ANGLE); }
			else { turnCCW(TURN_ANGLE); }
		}
		else { setXVel(0); setYVel(calcSpeed()); }
	}
	
	/*
	 * public method goEast.
	 * Takes no parameters and returns nothing.
	 * Makes the Harrier turn to the eastern direction and accelerate eastwards.
	 */
	public void goEast() {
		if(calcSpeed() < MAX_SPEED) { setXVel(getXVel() + ACCELERATION); }
		if(Math.abs(getAngle()) >= TURN_ANGLE) {
			if(getAngle() < Math.PI) { turnCCW(-TURN_ANGLE); }
			else { turnCCW(TURN_ANGLE); }
		}
		else { setXVel(calcSpeed()); setYVel(0); }
	}
	
	/*
	 * public method goWest.
	 * Takes no parameters and returns nothing.
	 * Makes the Harrier turn to the western direction and accelerate westwards.
	 */
	public void goWest() {
		if(calcSpeed() < MAX_SPEED) { setXVel(getXVel() - ACCELERATION); }
		if(Math.abs(getAngle() - Math.PI) >= TURN_ANGLE) {
			if(getAngle() < Math.PI) { turnCCW(TURN_ANGLE); }
			else { turnCCW(-TURN_ANGLE); }
		}
		else { setXVel(-calcSpeed()); setYVel(0); }
	}

	/*
	 * public method die.
	 * Takes no parameters and returns nothing.
	 * Resets the Harrier to its spawn.
	 */
	public void die() {
		setXPos(STARTPOS);
		setYPos(STARTPOS);
		setXVel(STARTVEL);
		setYVel(STARTVEL);
	}

}