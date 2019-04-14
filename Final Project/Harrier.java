//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

package Project;

/* 
 * Public class Harrier contains the behaviors and attributes of Harriers, one of the types of playable characters.
 */
public class Harrier extends Animal {

	int score;
	int visionRadius;

	public Harrier() {
		super();
		score = 0;
		visionRadius = 5;
	}
	
	public int getScore() { return this.score; }

	public void setScore(int score) { this.score = score; }

	public int getVision() { return this.visionRadius; }

	public void setVision(int visionRadius) { this.visionRadius = visionRadius; }

	/*
	 * public method interact.
	 * Takes Mouse as parameter and returns nothing.
	 * Processes an interaction between the Harrier and the Mouse.
	 */
	public void interact(Mouse m) {
		
	}
	
	/*
	 * public method interact.
	 * Takes GoldenMouse as parameter and returns nothing.
	 * Processes an interaction between the Harrier and the GoldenMouse.
	 */
	public void interact(GoldenMouse gm) {
		
	}
	
	/*
	 * public method interact.
	 * Takes Fox as parameter and returns nothing.
	 * Processes an interaction between the Harrier and the Fox.
	 */
	public void interact(Fox f) {
		
	}
	
	/*
	 * public method interact.
	 * Takes Tree as parameter and returns nothing.
	 * Processes an interaction between the Harrier and the Tree.
	 */
	public void interact(Tree tr) {
		
	}
	
	/*
	 * public method interact.
	 * Takes Twig as parameter and returns nothing.
	 * Processes an interaction between the Harrier and the Twig.
	 */
	public void interact(Twig tw) {
		
	}

}
