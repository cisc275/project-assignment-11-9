//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.awt.*;
import java.awt.image.BufferedImage;
/*
 * Public class TitleView handles displaying the Title Screen view, and holds the main function
 */
public class TitleView extends GameView implements java.io.Serializable {

	public final static int FRAME_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	public final static int FRAME_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

	public TitleView() {
		images = new BufferedImage[]{createBufferedImage("Screen_Title.png")};
		setBackground(Color.black);
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 * Displays the Title screen
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(images[0], 0, 0, TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, this);
	}
	
	public static void main(String[] args) {
		Controller c = new Controller();
		c.start();
	}

}
