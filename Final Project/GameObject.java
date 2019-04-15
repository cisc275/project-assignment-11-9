//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

/* 
 * Public abstract class GameObject outlines general behavior and attributes shared by all of the objects in the game.
 */
public abstract class GameObject {

	private int xPosition;
	private int yPosition;
	private int xWidth;
	private int yWidth;
	
	public GameObject() {
		xPosition = 0;
		yPosition = 0;
		xWidth = 1;
		yWidth = 1;
	}

	public int getXPos() { return this.xPosition; }

	public void setXPos(int xPos) { this.xPosition = xPos; }

	public int getYPos() { return this.yPosition; }

	public void setYPos(int yPos) { this.yPosition = yPos; }

	public int getXWidth() { return this.xWidth; }

	public void setXWidth(int xWidth) { this.xWidth = xWidth; }

	public int getYWidth() { return this.yWidth; }

	public void setYWidth(int yWidth) { this.yWidth = yWidth; }
	
}
