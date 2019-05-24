//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.*;

import javax.imageio.ImageIO;

/*
 * Public class OspreyEnding handles displaying the view when the player wins the Osprey Game
 */
public class OspreyEnding extends OspreyView{

	private Image background;
	private final static int HIGHSCORE_TEXT_X = 625;
	private final static int HIGHSCORE_TEXT_Y = 300; 
	private final static int FONT_SIZE = 30;
	private final static int ESCAPE_TEXT_X = 575;
	private final static int ESCAPE_TEXT_Y = 350;
	
	public OspreyEnding() {
		try{
			background = ImageIO.read(new File("src/images/OspreyBackground.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see OspreyView#paintComponent(java.awt.Graphics)
	 * Draws the background image for the win screen and displays the highscore
	 */
	protected void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0,TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, this);
		g.setColor(Color.WHITE);
		g.setFont(new Font(Font.SERIF, Font.BOLD, FONT_SIZE));
		g.drawString("Fastest Time: " + getFastTime() + " seconds", HIGHSCORE_TEXT_X, HIGHSCORE_TEXT_Y);
		g.drawString("Press ESC to return to the menu!", ESCAPE_TEXT_X, ESCAPE_TEXT_Y);
	}
}
