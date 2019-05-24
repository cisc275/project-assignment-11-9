//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/*
 * Public class TitleView handles displaying the Title Screen view, and holds the main function
 */
public class TitleView extends JPanel implements java.io.Serializable {

	private BufferedImage background;
	public final static int FRAME_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	public final static int FRAME_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

	public TitleView() {
		setLayout(null);
		setBackground(Color.black);
		background = createBufferedImage("Menu Background.png");
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
	
	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 * Displays the Title screen
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0,TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, this);
		g.setColor(Color.WHITE);
	}
	
	public static void main(String[] args) {
		Controller c = new Controller();
		c.start();
	}

}
