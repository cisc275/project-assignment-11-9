//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

import java.awt.image.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
/*
 * public class View handles the UI, and defines the main method.
 */
public class View {
	BufferedImage images;
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
	
	View() {
		buildImages();
		helper = new ViewHelper();
		helper.setImages(images);
		frame = new JFrame();
		frame.getContentPane().add(helper);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(frameWidth, frameHeight);
		frame.setVisible(true);
		frame.repaint();
	}
	
	public BufferedImage createImage(String path) {
		BufferedImage mine = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_ARGB);
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
	public void update(int xloc, int yloc, int direction) {
		helper.setX(xloc);
		helper.setY(yloc);
		helper.setDir(direction);
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
		View test = new View();
		for (int i = 0; i < 500; i += 5) {
			test.update(i, i, 1);
		}
	}
}
