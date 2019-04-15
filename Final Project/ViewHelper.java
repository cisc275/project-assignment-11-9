//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

import java.awt.Graphics;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.image.*;
/*
 * public class ViewHelper defines methods and variables to help the View in refreshing and updating the images.
 */
public class ViewHelper extends JPanel {
	private int xloc;
	private int yloc;
	private int direction;
	BufferedImage images;
	
	public void setX(int newX) {
		xloc = newX;
	}
	
	public void setY(int newY) {
		yloc = newY;
	}
	
	public void setDir(int newDir) {
		direction = newDir;
	}
	
	public void setImages(BufferedImage newIms) {
		images = newIms;
	}
	
	/*
	 * public method paint.
	 * Takes no parameters, and returns nothing.
	 * Repaints the ViewHelper Image.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		updateImage();
		g.drawRect(xloc, yloc, 25, 25);
		g.setColor(Color.GREEN);
		g.fillRect(xloc, yloc, 25, 25);
	}
	
	/*
	 * public method updateImage.
	 * Takes no parameters, and returns nothing.
	 * Updates the ViewHelper Image.
	 */
	public void updateImage() {
	}
}
