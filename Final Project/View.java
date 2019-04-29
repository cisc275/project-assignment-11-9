package Project;
//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.io.File;
import java.util.Scanner;
import java.util.TimerTask;
/*
 * public class View handles the UI, and defines the main method.
 */
public class View extends JFrame {

	private JButton button1 = new JButton("OspreyGame");
	private JButton button2 = new JButton("HarrierGame");
	public static JPanel contentPanel = new JPanel();
	private JPanel menu = new JPanel(); 
	CardLayout c1 = new CardLayout();
	final static int FRAMEWIDTH = 1600;
	final static int FRAMEHEIGHT = 900;
	private final int DRAWDELAY = 30;
	OspreyModel om;
	OspreyView ov;
	static Controller c = new Controller();
	
	

	public View() {


		super("Menu");
		button1 = new JButton("OspreyGame");
		button2 = new JButton("HarrierGame");
		
		menu.add(button1, BorderLayout.CENTER);
		menu.add(button2);
		
		actionListener a1 = new actionListener();
		
		button1.addActionListener(a1);
		button2.addActionListener(a1);
		
		
		contentPanel.setLayout(c1);
		contentPanel.add(menu, "menu");
		this.setContentPane(contentPanel);
		c1.first(contentPanel);
	}
	

	public static void main(String[] args) {
		
		View v = new View();
		v.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		v.setSize(1600,900);
		v.setVisible(true);

	}
	
	public int getDRAWDELAY() {
		return DRAWDELAY;
	}

	class actionListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			JButton src = (JButton) event.getSource();

			if(src.equals(button1)) {
				setVisible(false);
				c.gameOsprey();	
					
			}
			if(src.equals(button2)) {
				setVisible(false);
				c.gameHarrier();

			}

		}

	}
}