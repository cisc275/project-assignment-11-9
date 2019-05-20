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

public class HarrierEnding extends HarrierView{


	public String highScore;
	private Image background;


	public HarrierEnding() {
		highScore = "";
		try{
			background = ImageIO.read(new File("src/images/Harrier Ending .png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	protected void paintComponent(Graphics g) {
		highScore = GetHighScore();
		g.drawImage(background, 0, 0,TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, this);
		g.setColor(Color.WHITE);
		g.setFont(new Font(Font.SERIF, Font.BOLD, 30));
		g.drawString("HighScore: " + highScore, 650, 300);
		g.drawString("Press ESC to return to the menu!", 575, 350);
		

	}
}




