//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.image.*;

public abstract class GameView extends JPanel {

	protected boolean isDebug;
	protected String list[] = {"CUM", "SEX", "COK", "COC", "ASS", "VAG", "FUK", "FUC", "JIZ", "PEE", "POO", "HIV", "GUN","NIG"};
	protected BufferedImage[] images;
	protected BufferedImage[] animationFrames;
	

	public boolean getIsDebug() { return isDebug; }

	public void setIsDebug(boolean isDebug) { this.isDebug = isDebug; }

	/*
	 * This public method checks for inappropriate words and for only letters
	 * Takes in user inputed name
	 * Returns either the name typed if accepted or no for inappropriate
	 */
	public String filterInput(String name) {
		if(name.length() == 3 && name.matches("^[a-zA-Z]*$")) {
			for(int i = 0; i < list.length; i++) {
				if(name.toUpperCase().equals(list[i])) {
					return "no";
				}
			}
			return name;
		}
		return "no";
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