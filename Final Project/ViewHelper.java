//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.awt.Graphics;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.image.*;
/*
 * public class ViewHelper defines methods and variables to help the View in refreshing and updating the images.
 */
public class ViewHelper extends JPanel {/*
	private int[] xloc;
	private int[] yloc;
	private Direction[] direction;
	BufferedImage[] images;
<<<<<<< HEAD
	BufferedImage mine;
	
	public ViewHelper(BufferedImage[] newIms) {
		xloc = new int[1000];
		yloc = new int[1000];
		direction = new Direction[1000];
		images = new BufferedImage[1000];
		for (int i = 0; i < newIms.length; i++) {
			this.images[i] = newIms[i];
		}
=======
	
	public ViewHelper(BufferedImage[] images) {
		xloc = new int[1000];
		yloc = new int[1000];
		direction = new Direction[1000];
		this.images = images;
>>>>>>> aedce4deabd18a43f1e603839a6a76de455756cf
	}
	public void setX(int newX, int index) {
		xloc[index] = newX;
	}
	
	public void setY(int newY, int index) {
		yloc[index] = newY;
	}
	
	public void setDir(Direction newDir, int index) {
		direction[index] = newDir;
	}
	
	public void setImages(BufferedImage newIms, int index) {
		images[index] = newIms;
	}
	
	/*
	 * public method paint.
	 * Takes no parameters, and returns nothing.
	 * Repaints the ViewHelper Image.
	 *//*
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		updateImage();
		g.setColor(Color.GRAY);
		for (int i = 0; i < 1000 && xloc[i] != -1; i++) {
			g.drawImage(images[i], xloc[i], yloc[i], Color.gray, this);
		}
	}
	
	/*
	 * public method updateImage.
	 * Takes no parameters, and returns nothing.
	 * Updates the ViewHelper Image.
	 *//*
	public void updateImage() {
	}*/
}
