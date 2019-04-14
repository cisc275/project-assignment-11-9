//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

package Project;

/* 
 * public class Harrier contains the behaviors and attributes of Harriers.
 */
public class Harrier extends Animal {

	int score;
	int visionRadius;
	
	/*
	 * public method move.
	 * Takes no parameters and returns nothing.
	 * Updates the position, velocity, and direction of the animal.
	 */
	@Override
	public void move() {
	}

	public int getScore() { return score; }
	
	public void setScore(int score) { this.score = score; }
	
	public int getVision() { return visionRadius; }
	
	public void setVision(int visionRadius) { this.visionRadius = visionRadius; }
	
}
