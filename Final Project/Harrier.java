//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

/* 
 * Public class Harrier contains the behaviors and attributes of Harriers, one of the types of playable characters.
 */
public class Harrier extends Animal {

	private int score;
	private double visionRadius;
	static int GOINGNORTH = -3;
	static int GOINGWEST = -3;
	static int GOINGEAST = 3;
	static int GOINGSOUTH = 3;

	public Harrier() {
		setXPos(0);
		setYPos(0);
		setXWidth(60);
		setYWidth(60);
		setXVel(0);
		setYVel(0);
		updateDirection();
		score = 0;
		visionRadius = 5;
	}
	
	public void goNorth() { setYVel(GOINGNORTH); updateDirection(); }
	
	public void goSouth() { setYVel(GOINGSOUTH); updateDirection(); }
	
	public void goEast() { setXVel(GOINGEAST); updateDirection(); }
	
	public void goWest() { setXVel(GOINGWEST); updateDirection(); }
	
	
	
	public int getScore() { return this.score; }

	public void setScore(int score) { this.score = score; }

	public double getVision() { return this.visionRadius; }

	public void setVision(double visionRadius) { this.visionRadius = visionRadius; }

}
