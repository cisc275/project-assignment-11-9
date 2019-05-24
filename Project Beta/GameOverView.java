//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
 * Public class GameOverView handles displaying the view when the player loses the game
 */
public class GameOverView extends GameView {
	
	private Image background;
	private final static int TEXT_X = 250;
	private final static int TEXT_Y = 700; 
	private final static int FONT_SIZE = 42;
	
	public GameOverView() {
		try{
			background = ImageIO.read(new File("src/images/Game_Over.1 (2).png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 * Also paints the end screen for Game Over.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0,TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, this);
		g.setFont(new Font(Font.SERIF, Font.BOLD, FONT_SIZE));
		g.drawString("You were unable to fly safely! Press ESC to return to the menu!", TEXT_X,TEXT_Y);
	}
}
