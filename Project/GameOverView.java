//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.awt.*;
import java.awt.image.BufferedImage;
/*
 * Public class GameOverView handles displaying the view when the player loses the game
 */
public class GameOverView extends GameView {
	
	private final static int FONT_SIZE = 42;
	private final static double WIDTH_SCALAR = .21;
	private final static double HEIGHT_SCALAR = .63;
	
	public GameOverView() {
		images = new BufferedImage[]{createBufferedImage("Screen_GameOver.png")};
	}

	/*
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 * Paints the end screen for Game Over.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(images[0], 0, 0, TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, this);
		g.setFont(new Font(Font.SERIF, Font.BOLD, FONT_SIZE));
		g.setColor(Color.WHITE);
		g.drawString("You were unable to fly safely! Press ESC to return to the menu!",
					(int)(TitleView.FRAME_WIDTH * WIDTH_SCALAR), (int)(TitleView.FRAME_HEIGHT * HEIGHT_SCALAR));
	}
	
}
