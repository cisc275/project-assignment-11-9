//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/*
 * Public class HarrierEnding handles displaying the view when the player wins the Harrier Game
 */
public class HarrierEnding extends HarrierView{


	private Image background;
	private final static int HIGHSCORE_TEXT_X = 650;
	private final static int HIGHSCORE_TEXT_Y = 300; 
	private final static int FONT_SIZE = 30;
	private final static int ESCAPE_TEXT_X = 575;
	private final static int ESCAPE_TEXT_Y = 350;


	public HarrierEnding() {
		try{
			background = ImageIO.read(new File("src/images/Harrier Ending .png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/*
	 * (non-Javadoc)
	 * @see HarrierView#paintComponent(java.awt.Graphics)
	 * Paints the end screen for a Harrier win.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0,TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, this);
		g.setColor(Color.WHITE);
		g.setFont(new Font(Font.SERIF, Font.BOLD, FONT_SIZE));
		g.drawString("HighScore: " + getHighScore(), HIGHSCORE_TEXT_X, HIGHSCORE_TEXT_Y);
		g.drawString("Press ESC to return to the menu!", ESCAPE_TEXT_X, ESCAPE_TEXT_Y);
		

	}
}



