//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import javax.swing.*;

public abstract class GameView {
	
	protected JFrame frame;
	
	public GameView() {
		frame = new JFrame();
		frame.setSize(TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void addListener(Controller c) {
		frame.addKeyListener(c);
	}

}
