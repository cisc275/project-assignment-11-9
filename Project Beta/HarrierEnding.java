//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.awt.*;
import java.awt.image.BufferedImage;
/*
 * Public class HarrierEnding handles displaying the view when the player wins the Harrier Game
 */
public class HarrierEnding extends HarrierView {

	private final static int FONT_SIZE = 30;
	private final static double SCORE_W_SCALAR = .44;
	private final static double SCORE_H_SCALAR = .25;
	private final static double TEXT_W_SCALAR = .4;
	private final static double TEXT_H_SCALAR = .25;
	private final static int TEXT_H_OFFSET = 50;

	public HarrierEnding() {
		images = new BufferedImage[]{createBufferedImage("Harrier Ending .png")};
	}

	/*
	 * (non-Javadoc)
	 * @see HarrierView#paintComponent(java.awt.Graphics)
	 * Paints the end screen for a Harrier win.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(images[0], 0, 0,TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, this);
		g.setFont(new Font(Font.SERIF, Font.BOLD, FONT_SIZE));
		g.setColor(Color.WHITE);
		g.drawString("HighScore: " + getHighScore(),
					(int)(TitleView.FRAME_WIDTH * SCORE_W_SCALAR), (int)(TitleView.FRAME_HEIGHT * SCORE_H_SCALAR));
		g.drawString("Press ESC to return to the menu!",
					(int)(TitleView.FRAME_WIDTH * TEXT_W_SCALAR), (int)(TitleView.FRAME_HEIGHT * TEXT_H_SCALAR) + TEXT_H_OFFSET);
	}
	
}
