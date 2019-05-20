//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/*
 * Public class TitleView handles the UI, and defines the main method.
 */
public class TitleView extends JPanel implements java.io.Serializable {

	private JButton buttonO;
	private JButton buttonH;
	private Image background;
	public final static int FRAME_WIDTH = 1600;
	public final static int FRAME_HEIGHT = 900;

	public TitleView() {
		try{
			background = ImageIO.read(new File("src/images/MenuScreen.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		buttonO = new JButton("Osprey");
		buttonH = new JButton("Harrier");
		add(buttonO);
		add(buttonH);
		setLayout(null);
		int buttonWidth = FRAME_WIDTH / 3;
		int buttonHeight = FRAME_HEIGHT / 5;
		buttonO.setBounds(FRAME_WIDTH / 4 - buttonWidth / 2, FRAME_HEIGHT / 2 - buttonHeight / 2, buttonWidth, buttonHeight);
		buttonH.setBounds(FRAME_WIDTH * 74 / 100 - (buttonWidth+100) / 2, FRAME_HEIGHT / 2 - buttonHeight / 2, buttonWidth, buttonHeight);
		buttonO.setFont(new Font(Font.SERIF, Font.PLAIN, 50));
		buttonH.setFont(new Font(Font.SERIF, Font.PLAIN, 50));
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0,TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, this);
	}
	
	public JButton getButtonO() { return buttonO; }
	
	public JButton getButtonH() { return buttonH; }
	
	/*
	 * public method addListener.
	 * Takes Controller as parameter and returns nothing.
	 * Allows the controller to listen to the buttons.
	 */
	public void addListener(Controller c) {
		buttonO.addActionListener(c);
		buttonH.addActionListener(c);
	}

}