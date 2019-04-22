//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.io.File;
import java.util.Scanner;
/*
 * public class View handles the UI, and defines the main method.
 */
public class View {
	public int getGame() {
		Scanner scan = new Scanner(System.in);
		String input = "";
		while(!(input.equals("1") || input.equals("2"))) {
			System.out.println("Enter 1 for Osprey, 2 for Harrier");
			input = scan.nextLine();
		}
		int num = Integer.parseInt(input);
		return num;
	}
	
	public static void main(String[] args) {
		Controller c = new Controller();
		c.setNum(c.view.getGame());
		c.start(c.getNum());
	}
	/*
	BufferedImage[] images;
	int imageNum;
	int picNum;
	int frameCount;
	int frameWidth = 500;
	int frameHeight = 500;
<<<<<<< HEAD
	int imgWidth = 25;
	int imgHeight = 25;
=======
	int imgWidth = 2500;
	int imgHeight = 2500;
>>>>>>> aedce4deabd18a43f1e603839a6a76de455756cf
	JFrame frame;
	ViewHelper helper;
	
	/*
	 * public method createImage
	 * Takes no parameters, and returns nothing.
	 * Creates a gameImage to be used by the GUI.
	 */
	/*
	View(int[] objects) {
<<<<<<< HEAD
		buildImages(objects);
=======
		buildImages();
>>>>>>> aedce4deabd18a43f1e603839a6a76de455756cf
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
	 *//*
<<<<<<< HEAD
	public void buildImages(int[] types) {
		images = new BufferedImage[types.length];
		for (int i = 0; i < types.length; i++) {
			images[i] = createImage(types[i]);
		}
=======
	public void buildImages() {
>>>>>>> aedce4deabd18a43f1e603839a6a76de455756cf
	}
	
	/*
	 * public method update.
	 * Takes no parameters, and returns nothing.
	 * Updates the image displayed by the GUI.
	 *//*
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
	 *//*
	public static void main(String args[]) {
		Controller c = new Controller();
		c.start();
	}*/
}
