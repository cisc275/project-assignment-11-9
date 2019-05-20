package Project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.*;

import javax.imageio.ImageIO;

public class OspreyEnding extends OspreyView{

	
	private Image background;
	private String fastTime;
	
	public OspreyEnding() {
		fastTime = "";
		try{
			background = ImageIO.read(new File("src/images/OspreyBackground.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/*
	 * Draws the background image for the win screen, displays the highscore
	 * Takes in graphics
	 * Returns Nothing
	 */
	protected void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0,TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, this);
		fastTime = getFastTime();
		g.setColor(Color.WHITE);
		g.setFont(new Font(Font.SERIF, Font.BOLD, 30));
		g.drawString("Fastest Time: " + fastTime + " seconds", 625, 300);
		g.drawString("Press ESC to return to the menu!", 575, 350);
		//g.drawString("Completed Time" + Osprey.displayMin +":"+ Osprey.displaySec + Osprey.display2Sec, 500,500);
	}
}
