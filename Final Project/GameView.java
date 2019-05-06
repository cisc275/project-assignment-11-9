//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.image.*;

public abstract class GameView {
	
	protected JFrame frame;
	
	public GameView() {
		frame = new JFrame();
		frame.setSize(TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void addListener(Controller c) {
		frame.addKeyListener(c);
	}
	
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
