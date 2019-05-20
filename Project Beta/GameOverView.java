package Project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GameOverView extends GameView {
	
	private Image background;
	
	public GameOverView() {
		try{
			background = ImageIO.read(new File("src/images/Game_Over.1 (2).png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0,TitleView.FRAME_WIDTH-75, TitleView.FRAME_HEIGHT, this);
		g.setFont(new Font(Font.SERIF, Font.BOLD, 42));
		g.drawString("You were unable to fly safely! Press ESC to return to the menu!", 200,600);
	}
}
