//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.io.File;
/*
 * public class View handles the UI, and defines the main method.
 */
public class View {
	BufferedImage[] images;
	int imageNum;
	int picNum;
	int frameCount;
	int frameWidth = 500;
	int frameHeight = 500;
	int imgWidth = 2500;
	int imgHeight = 2500;
	JFrame frame;
	ViewHelper helper;
	
	/*
	 * public method createImage
	 * Takes no parameters, and returns nothing.
	 * Creates a gameImage to be used by the GUI.
	 */
	
	View(int[] objects) {
		buildImages();
		helper = new ViewHelper(images);
		frame = new JFrame();
		frame.getContentPane().add(helper);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(frameWidth, frameHeight);
		frame.setVisible(true);
		frame.repaint();
	}
	
	void addListener(Controller c) {
		frame.addKeyListener(c);
	}
	public BufferedImage createImage(int type) {
		BufferedImage mine = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics g = mine.createGraphics();
		if (type == 0) {
			g.setColor(Color.GREEN);
		} else {
			g.setColor(Color.BLUE);
		}
		g.fillRect(0, 0, mine.getWidth(), mine.getHeight());
		return mine;
	}
	
	/*
	 * public method buildImages.
	 * Takes no parameters, and returns nothing.
	 * Builds all the images that make up the whole game image.
	 */
	public void buildImages() {
	}
	
	/*
	 * public method update.
	 * Takes no parameters, and returns nothing.
	 * Updates the image displayed by the GUI.
	 */
	public void update(int xloc[], int yloc[], Direction direction[]) {
		for (int i = 0; i < xloc.length; i++) {
			helper.setX(xloc[i], i);
			helper.setY(yloc[i], i);
			helper.setDir(direction[i], i);
		}
		frame.repaint();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * public static method main.
	 * Takes parameter args: String[]. Returns nothing.
	 * Runs the code.
	 */
	public static void main(String args[]) {
		Controller c = new Controller();
		c.start();
	}
}
