//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * Public class TitleView handles the UI, and defines the main method.
 */
public class TitleView extends JFrame {

	private JButton buttonO;
	private JButton buttonH;
	private JPanel menu;
	public static JPanel contentPanel;
	private CardLayout layout;
	final static int FRAME_WIDTH = 1600;
	final static int FRAME_HEIGHT = 900;

	public TitleView() {
		super("Menu");
		buttonO = new JButton("Osprey");
		buttonH = new JButton("Harrier");
		menu = new JPanel();
		
		contentPanel = new JPanel();
		layout = new CardLayout();

		menu.add(buttonO, BorderLayout.CENTER);
		menu.add(buttonH);

		contentPanel.setLayout(layout);
		contentPanel.add(menu, "menu");
		this.setContentPane(contentPanel);
		layout.first(contentPanel);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setVisible(true);
	}
	
	public JButton getButtonO() { return buttonO; }
	
	public JButton getButtonH() { return buttonH; }
	
	public void addListener(Controller c) {
		buttonO.addActionListener(c);
		buttonH.addActionListener(c);
	}

}