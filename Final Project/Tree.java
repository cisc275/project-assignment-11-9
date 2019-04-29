//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;

/* 
 * Public class Tree contains the behaviors and attributes of Trees, the obstacles in the Harrier game.
 */
import java.util.concurrent.TimeUnit;
import java.io.*;
public class Tree extends GameObject implements HarrierAble {
	
	long lastTime;
	
	public Tree(double x, double y) {
		setXPos(x);
		setYPos(y);
		setXWidth(120);
		setYWidth(120);
		lastTime = System.nanoTime();
	}
	
	/*
	 * public method interact.
	 * Takes Harrier as parameter and returns nothing.
	 * Processes an interaction between the Harrier and the Tree.
	 */
	public void interact(Harrier h) {
		long newTime = System.nanoTime();
		if ((newTime - lastTime)/1000000000 > 1) {
			h.setScore(h.getScore() - 10);
			h.setVision(h.getVision() - 1);
			lastTime = newTime;
		}
		h.setXVel(0);
		h.setYVel(0);
		h.setXPos(h.getXPos() - h.getXVel());
		h.setYPos(h.getYPos() - h.getYVel());
	}
	
	@Override
	public boolean isTree() {
		return true;
	}
	
}
