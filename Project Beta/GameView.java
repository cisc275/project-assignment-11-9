//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.image.*;

public abstract class GameView extends JPanel {

	protected boolean isDebug;
	protected BufferedImage[] images;
	protected BufferedImage[] animationFrames;
	
	public boolean getIsDebug() { return isDebug; }
	
	public void setIsDebug(boolean isDebug) { this.isDebug = isDebug; }
	
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
