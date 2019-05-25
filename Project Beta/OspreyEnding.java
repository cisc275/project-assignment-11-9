//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.awt.*;
import java.awt.image.BufferedImage;
/*
 * Public class OspreyEnding handles displaying the view when the player wins the Osprey Game
 */
public class OspreyEnding extends OspreyView {

	private final static int FONT_SIZE = 30;
	private final static double SCORE_W_SCALAR = .415;
	private final static double SCORE_H_SCALAR = .25;
	private final static double TEXT_W_SCALAR = .4;
	private final static double TEXT_H_SCALAR = .25;
	private final static int TEXT_H_OFFSET = 50;
	
	public OspreyEnding() {
		images = new BufferedImage[]{createBufferedImage("OspreyBackground.png")};
	}
	
	/*
	 * (non-Javadoc)
	 * @see OspreyView#paintComponent(java.awt.Graphics)
	 * Draws the background image for the win screen and displays the highscore
	 */
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(images[0], 0, 0,TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, this);
		g.setFont(new Font(Font.SERIF, Font.BOLD, FONT_SIZE));
		g.setColor(Color.WHITE);
		g.drawString("Fastest Time: " + getFastTime() + " seconds",
					(int)(TitleView.FRAME_WIDTH * SCORE_W_SCALAR), (int)(TitleView.FRAME_HEIGHT * SCORE_H_SCALAR));
		g.drawString("Press ESC to return to the menu!",
					(int)(TitleView.FRAME_WIDTH * TEXT_W_SCALAR), (int)(TitleView.FRAME_HEIGHT * TEXT_H_SCALAR) + TEXT_H_OFFSET);
	}
	
}
