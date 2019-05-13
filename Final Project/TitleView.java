//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

/*
 * Public class TitleView handles the UI, and defines the main method.
 */
public class TitleView extends JFrame {

	public static final int FRAME_WIDTH = 1600;
	public static final int FRAME_HEIGHT = 900;
	Image[] images;
	Controller c = new Controller();
	JFrame frame = new JFrame("EstuaryGames");
	CardLayout c1 = new CardLayout();
	OspreyView ov;
	JPanel contentPanel, menu, endgame1, endgame2;
	OspreyView ospreyPane;
	HarrierView harrierPane;
	JButton game1, game2;
	
	

	public TitleView() {
		contentPanel = new JPanel();
		contentPanel.setLayout(c1);
		createPanels();
		
		contentPanel.add(menu,"m");
		contentPanel.add(ospreyPane, "o");
		contentPanel.add(harrierPane, "h");
		contentPanel.add(endgame1, "e1");
		contentPanel.add(endgame2, "e2");
		
		c1.show(contentPanel, "m");
		
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.add(contentPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.addKeyListener(c);
		frame.setFocusable(true);
		
		
	}
	
	public void createPanels() {
		menu = new JPanel();
		menu.setLayout(null);
		menu.setBackground(Color.CYAN);
		
		ospreyPane = new OspreyView();
		ospreyPane.setLayout(null);
		
		harrierPane = new HarrierView();
		harrierPane.setLayout(null);
		
		game1 = new JButton("Osprey Game");
		game2 = new JButton("Harrier Game");
		menu.add(game1);
		game1.setBounds(600, 400, 150, 100);
		menu.add(game2);
		game2.setBounds(750,400,150,100);
		
		endgame1 = new JPanel();
		endgame1.setLayout(null);
		endgame2 = new JPanel();
		endgame2.setLayout(null);
		
	}
	
	
	

	
	public void ospreyUpdate(Osprey osprey, ArrayList<Fish> fish, ArrayList<Seaweed> seaweed) {
		ospreyPane.setOsprey(osprey);
		ospreyPane.setFish(fish);
		ospreyPane.setSeaweed(seaweed);
		frame.repaint();
	}
	
	public void harrierUpdate(Harrier harrier, ArrayList<Fox> foxes, ArrayList<Mouse> mice, ArrayList<Twig> twigs, ArrayList<Tree> trees, HarrierModel.Tutorial tutState) {
		harrierPane.setHarrier(harrier);
		harrierPane.setFoxes(foxes);
		harrierPane.setMice(mice);
		harrierPane.setTwigs(twigs);
		harrierPane.setTrees(trees);
		if (tutState == HarrierModel.Tutorial.ONE) {
			harrierPane.isTutorialBeginning = true;
		} else {
			harrierPane.isTutorialBeginning = false;
		}
		frame.repaint();
	}
	
	
	public void addListener(Controller c) {
		game1.addActionListener(c);
		game2.addActionListener(c);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				Controller c = new Controller();
				c.start();
			}
		});

	}
//	public BufferedImage createBufferedImage(String fileName) {
//    	BufferedImage bufferedImage;
//    	try {
//    		String path = "src/images/";
//    		path += fileName;
//    		bufferedImage = ImageIO.read(new File(path));
//    		return bufferedImage;
//    	} catch (IOException e) {
//    		e.printStackTrace();
//    	}
//    	return null;
//	}
}
