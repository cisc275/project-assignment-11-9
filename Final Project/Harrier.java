//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;

/* 
 * Public class Harrier contains the behaviors and attributes of Harriers, one of the types of playable characters.
 */
public class Harrier extends Animal {

	private int score;
	private double visionRadius;
	static int INITIALVISION = 250;
	static int STARTPOS = 0;
	static int STARTVEL = 0;
	static int SIZE = 60;
	final static double MAX_SPEED = 10;
	final static int SPEED_STEP = 3;
	final static double ACCELERATION = MAX_SPEED / SPEED_STEP;

	public Harrier() {
		setXPos(STARTPOS);
		setYPos(STARTPOS);
		setXWidth(SIZE);
		setYWidth(SIZE);
		setXVel(STARTVEL);
		setYVel(STARTVEL);
		updateDirection();
		score = 0;
		visionRadius = INITIALVISION;
	}
	
	public int getScore() { return this.score; }

	public void setScore(int score) { this.score = score; }

	public double getVision() { return this.visionRadius; }

	public void setVision(double visionRadius) { this.visionRadius = visionRadius; }
	
	public void goNorth() {
		if(getYVel() > -MAX_SPEED) {
			setYVel(getYVel() - ACCELERATION);
			updateDirection();
		}
	}
	
	public void goSouth() {
		if(getYVel() < MAX_SPEED) {
			setYVel(getYVel() + ACCELERATION);
			updateDirection();
		}
	}
	
	public void goEast() {
		if(getXVel() < MAX_SPEED) {
			setXVel(getXVel() + ACCELERATION);
			updateDirection();
		}
	}
	
	public void goWest() {
		if(getXVel() > -MAX_SPEED) {
			setXVel(getXVel() - ACCELERATION);
			updateDirection();
		}
	}

	public void die() {
		setXPos(STARTPOS);
		setYPos(STARTPOS);
		setXVel(STARTVEL);
		setYVel(STARTVEL);
		updateDirection();
	}

}