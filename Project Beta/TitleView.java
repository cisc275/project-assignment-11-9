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
 * Public class TitleView handles the UI, and defines the main method.
 */
public class TitleView extends JPanel implements java.io.Serializable {

	private BufferedImage background;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	//public final static int FRAME_WIDTH = FRAME_HEIGHT_temp;
	//public final static int FRAME_HEIGHT = FRAME_WIDTH_temp;
	
	public final static int FRAME_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	public final static int FRAME_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

	public TitleView() {
		setLayout(null);
		setBackground(Color.black);
		background = createBufferedImage("Menu Background.png");
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
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0,TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, this);
		g.setColor(Color.WHITE);
	}

}
