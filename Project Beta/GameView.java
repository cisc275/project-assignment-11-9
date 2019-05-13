//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.image.*;

public abstract class GameView {
	
	protected JFrame frame;
	protected boolean isDebug;
	protected BufferedImage[] images;
	protected BufferedImage[] animationFrames;
	
	public GameView() {
		frame = new JFrame();
		frame.setSize(TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		isDebug = false;
	}
	
	public boolean getIsDebug() { return isDebug; }
	
	public void setIsDebug(boolean isDebug) { this.isDebug = isDebug; }
	
	/*
	 * public method addListener.
	 * Takes Controller as parameter and returns nothing.
	 * Allows the controller to listen to the input in the frame.
	 */
	public void addListener(Controller c) {
		frame.addKeyListener(c);
	}
	
	/*
	 * public method createBufferedImage.
	 * Takes String as parameter and returns nothing.
	 * Reads and creates a BufferedImage object of the image with the given filename.
	 */
	public BufferedImage createBufferedImage(String fileName) {
    	BufferedImage bufferedImage;
    	try {
    		String path = "src/images/";
    		path += fileName;
    		bufferedImage = ImageIO.read(new File(path));
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
	}

}
