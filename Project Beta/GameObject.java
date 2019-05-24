//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;

/* 
 * Public abstract class GameObject outlines general behavior and attributes shared by all of the objects in the game.
 */
public abstract class GameObject implements java.io.Serializable {

	private double xPosition;
	private double yPosition;
	private double xWidth;
	private double yWidth;

	public double getXPos() { return this.xPosition; }

	public void setXPos(double xPos) { this.xPosition = xPos; }

	public double getYPos() { return this.yPosition; }

	public void setYPos(double yPos) { this.yPosition = yPos; }

	public double getXWidth() { return this.xWidth; }

	public void setXWidth(double xWidth) { this.xWidth = xWidth; }

	public double getYWidth() { return this.yWidth; }

	public void setYWidth(double yWidth) { this.yWidth = yWidth; }
	
	/*
	 * public method calcDist.
	 * Parameters: none
	 * Returns: double
	 * Returns the distance of the Game Object from the origin.
	 */
	public double calcDist() { return Math.sqrt(xPosition * xPosition + yPosition * yPosition); }
	
	/*
	 * public method calcDist.
	 * Parameters:
	 *     GameObject: go
	 * Returns: double
	 * Returns the distance between the GameObjects.
	 */
	public double calcDist(GameObject go) {
		double dx = this.xPosition - go.xPosition;
		double dy = this.yPosition - go.yPosition;
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	/*
	 * public method getApproximateDirection.
	 * Parameters:
	 *     GameObject: go
	 * Returns: Direction
	 * Returns the approximate direction from this gameObject to the parameter go.
	 */
	public Direction getApproximateDirection(GameObject go) {
		if (Math.abs(go.xPosition - this.xPosition) > Math.abs(go.yPosition - this.yPosition)) {
			if (this.xPosition < go.xPosition) { return Direction.WEST; } 
			else { return Direction.EAST; }
		} else {
			if (this.yPosition < go.yPosition) { return Direction.NORTH; }
			else { return Direction.SOUTH; }
		}
	}

}