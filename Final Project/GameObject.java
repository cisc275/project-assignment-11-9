//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

package Project;

/* 
 * Public class GameObject outlines general behavior and attributes shared by all Objects in the game.
 */
public abstract class GameObject {
	
	int xPosition;
	int yPosition;
	int xWidth;
	int yWidth;
	
	public int getXPos() { return this.xPosition; }
	
	public void setXPos(int xPos) { xPosition = xPos; }
	
	public int getYPos() { return this.yPosition; }
	
	public void setYPos(int yPos) { yPosition = yPos; }
	
	public int getXWidth() { return this.xWidth; }
	
	public void setXWidth(int xWidth) { this.xWidth = xWidth; }
	
	public int getYWidth() { return this.yWidth; }
	
	public void setYWidth(int yWidth) { this.yWidth = yWidth; }
}
