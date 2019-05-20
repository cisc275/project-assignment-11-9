//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;

/* 
 * Public abstract class GameObject outlines general behavior and attributes shared by all of the objects in the game.
 */
public abstract class GameObject {

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
	
<<<<<<< HEAD
	/*
	 * public method calcDist.
	 * Takes no parameters and returns double.
	 * Calculates the distance of the Game Object from the origin.
	 */
	public double calcDist() { return Math.sqrt(xPosition * xPosition + yPosition * yPosition); }

}
=======
	public double radius(double objectY, double objectX) { 
		double x = Math.abs(objectX) - Math.abs(this.getXPos());
		double y = Math.abs(objectY) - Math.abs(this.getYPos());
		return Math.sqrt(y*y + x*x);
	}
	
	public Direction getApproximateDirection(double objectY, double objectX) {
		if (Math.abs(objectX - this.getXPos()) > Math.abs(objectY - this.getYPos())) {
			if (this.getXPos() < objectX) {
				return Direction.WEST;
			} else {
				return Direction.EAST;
			}
		} else {
			if (this.getYPos() < objectY) {
				return Direction.NORTH;
			} else {
				return Direction.SOUTH;
			}
		}
	}
}
>>>>>>> a50fe777534a312aa19768ecfe732028af715260
