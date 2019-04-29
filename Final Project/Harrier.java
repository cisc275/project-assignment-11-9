//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

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
	static int STARTVEL = 0;
	static int SIZE = 60;

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
	
	public void goNorth() { setYVel(GOINGNORTH); updateDirection(); }
	
	public void goSouth() { setYVel(GOINGSOUTH); updateDirection(); }
	
	public void goEast() { setXVel(GOINGEAST); updateDirection(); }
	
	public void goWest() { setXVel(GOINGWEST); updateDirection(); }
	
	
	
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
}