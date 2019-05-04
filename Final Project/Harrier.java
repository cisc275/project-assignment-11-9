//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
/* 
 * Public class Harrier contains the behaviors and attributes of Harriers, one of the types of playable characters.
 */
public class Harrier extends Animal {

	private int score;
	private double visionRadius;
	static int GOINGNORTH = -10;
	static int GOINGWEST = -10;
	static int GOINGEAST = 10;
	static int GOINGSOUTH = 10;
	static int INITIALVISION = 500;
	static int STARTPOS = 0;
	static int STARTVEL = 5;
	static int SIZE = 60;

	public Harrier() {
		setXPos(STARTPOS);
		setYPos(STARTPOS);
		setXWidth(SIZE);
		setYWidth(SIZE);
		setXVel(STARTVEL);
		setYVel(0);
		updateDirection();
		score = 0;
		visionRadius = INITIALVISION;
	}
	
	public void goNorth() {
		if (goingSouth()) {
			if (goingWest()) {
				goWest();
			} else if (goingEast()) {
				goEast();
			} else {
				goEast();
			}
		} else { //yVelocity is negative
			if (goingWest()) { //xVelocity is negative
				setXVel(getXVel() + 1);
				setYVel(getYVel() - 1);
			} else if (goingEast()) {
				setXVel(getXVel() - 1);
				setYVel(getYVel() - 1);
			}
		}
	}
	
	public void goSouth() {
		if (goingNorth()) {
			if (goingWest()) {
				goWest();
			} else if (goingEast()) {
				goEast();
			} else {
				goWest();
			}
		} else {
			if (goingWest()) {
				setXVel(getXVel() + 1);
				setYVel(getYVel() + 1);
			} else if (goingEast()) {
				setXVel(getXVel() - 1);
				setYVel(getYVel() + 1);
			}
		}
	}
	
	public void goEast() {
		if (goingWest()) {
			if (goingNorth()) {
				goNorth();
			} else if (goingSouth()) {
				goSouth();
			} else {
				goSouth();
			}
		} else {
			if (goingNorth()) {
				setXVel(getXVel() + 1);
				setYVel(getYVel() + 1);
			} else if (goingSouth()) {
				setXVel(getXVel() + 1);
				setYVel(getYVel() - 1);
			}
		}
	}
	
	public void goWest() {
		if (goingEast()) {
			if (goingNorth()) {
				goNorth();
			} else if (goingSouth()) {
				goSouth();
			} else {
				goNorth();
			}
		} else {
			if (goingNorth()) {
				setXVel(getXVel() - 1);
				setYVel(getYVel() + 1);
			} else if (goingSouth()) {
				setXVel(getXVel() - 1);
				setYVel(getYVel() - 1);
				
			}
		}
	}
	
	
	
	public int getScore() { return this.score; }

	public void setScore(int score) { this.score = score; }

	public double getVision() { return this.visionRadius; }

	public void setVision(double visionRadius) { this.visionRadius = visionRadius; }

	public void die() {
		setXPos(STARTPOS);
		setYPos(STARTPOS);
		setXVel(STARTVEL);
		setYVel(STARTVEL);
		updateDirection();
	}
	@Override
	public boolean isHarrier() {
		return true;
	}
	
	public boolean goingSouth() {
		return (getYVel() > 0);
	}
	
	public boolean goingNorth() {
		return (getYVel() < 0);
	}
	
	public boolean goingWest() {
		return (getXVel() < 0);
	}
	
	public boolean goingEast() {
		return (getXVel() > 0);
	}
}