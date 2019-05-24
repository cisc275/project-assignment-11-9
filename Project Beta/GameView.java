//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.image.*;

/*
 * Public abstract class GameView defines functions for the various Views used by the Game.
 */
public abstract class GameView extends JPanel {

	protected boolean isDebug;
	protected BufferedImage[] images;
	protected BufferedImage[] animationFrames;
	protected String list[] = {"CUM", "SEX", "COK", "COC", "ASS", "VAG", "FUK", "FUC", "JIZ", "PEE", "POO", "HIV", "GUN","NIG"};
	
	public boolean getIsDebug() { return isDebug; }
	
	public void setIsDebug(boolean isDebug) { this.isDebug = isDebug; }
	
	/*
	 * public method filterInput.
	 * Parameters:
	 *     String: name
	 * Returns: String
	 * Checks name parameter for inappropriate words and wrong-sized words.
	 * Returns either the name typed if accepted or no if inappropriate.
	 */
	public String filterInput(String name) {
		if(name.length() == 3 && name.matches("^[a-zA-Z]*$")) {
			for(int i = 0; i < list.length; i++) {
				if(name.toUpperCase().equals(list[i])) {
					return "no";
				}
			}
			return name;
		} else {
			return "no";
		}
	}
	
	/*
	 * public method createBufferedImage.
	 * Parameters:
	 *     String: fileName
	 * Returns: BufferedImage
	 * Reads the file indicated by fileName, and creates a BufferedImage from that file.
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