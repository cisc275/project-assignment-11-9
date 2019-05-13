//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.util.*;

import static org.junit.Assert.fail;

import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

/* 
 * Public Controller class runs the game.
 */
public class Controller implements ActionListener, KeyListener {

	private TitleView tv;
	private OspreyModel om;
	private OspreyView ov;
	private HarrierModel hm;
	private HarrierView hv;
	private static boolean paused;
	private static String game;
	private Action ospreyAction;
	private Action harrierAction;
	final static int TICK_TIME = 60;
	private boolean canSaveLoad = false;

	public Controller() { 
		paused = false; 
		ospreyAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!paused) {
					om.update();
					tv.ospreyUpdate(OspreyModel.getOsprey(), om.getFish(), om.getSeaweed());
				}
				else {
					try { Thread.sleep(TICK_TIME); }
					catch(InterruptedException e) { e.printStackTrace(); }
				}
			}

		};
		harrierAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!paused) {
					hm.update();
					tv.harrierUpdate(HarrierModel.getHarrier(), hm.getFoxes(), hm.getMice(), hm.getTwigs(), hm.getTrees(), hm.tutorialState);
				}
				else {
					try { Thread.sleep(TICK_TIME); }
					catch(InterruptedException e) { e.printStackTrace(); }
				}

			}

		};

	}

	public void start() {
		game = "Title";
		tv = new TitleView();
		tv.addListener(this);
		om = null;
		ov = null;
		hm = null;
		hv = null;
	}

	public void startOsprey() {
		game = "Osprey";
		om = new OspreyModel();
		Timer timer = new Timer(TICK_TIME, ospreyAction);
		timer.start();
	}

	public void startHarrier() {
		game = "Harrier";
		hm = new HarrierModel();
		Timer timer = new Timer(TICK_TIME, harrierAction);
		timer.start();
	}

	/* 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (Controller.game.equals("Osprey")) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) { OspreyModel.getOsprey().dive(); }
			if(e.getKeyCode() == KeyEvent.VK_P) { Controller.paused = !paused;}
		}

		else if(Controller.game.contentEquals("Harrier")) {
				if (e.getKeyCode() == KeyEvent.VK_UP) { HarrierModel.getHarrier().goNorth(); }
				if (e.getKeyCode() == KeyEvent.VK_DOWN) { HarrierModel.getHarrier().goSouth(); }
				if (e.getKeyCode() == KeyEvent.VK_LEFT) { HarrierModel.getHarrier().goWest(); }
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) { HarrierModel.getHarrier().goEast(); }
				if(e.getKeyCode() == KeyEvent.VK_P) { Controller.paused = !paused;}
			}
		
		
			
		}

		/* 
		 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
		 */
		@Override
		public void keyReleased(KeyEvent e) {
			if (Controller.game.equals("Osprey")) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) { OspreyModel.getOsprey().rise(); }
			}
			if (e.getKeyCode() == KeyEvent.VK_A) {
				canSaveLoad = !canSaveLoad;
				System.out.println("a");
			} else if (canSaveLoad) {
				System.out.println("b");
				if (e.getKeyCode() == KeyEvent.VK_S) {
					save();
				} else if (e.getKeyCode() == KeyEvent.VK_L) {
					load();
				}
			}
		}

		/*
		 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
		 */
		@Override
		public void keyTyped(KeyEvent e) {
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton src = (JButton) e.getSource();
			if (src.equals(tv.game1)) { tv.c1.show(tv.contentPanel, "o"); startOsprey(); }
			else if (src.equals(tv.game2)) { tv.c1.show(tv.contentPanel, "h"); startHarrier(); }
		}
		
		public void save() {
			try {
				FileOutputStream fos;
				if (game.equals("Osprey")) {
					fos = new FileOutputStream("ospreyfile.ser");
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(om);
					oos.close();
					System.out.println(om.getSeaweed().size());
				} else {
					fos = new FileOutputStream("harrierfile.ser");
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(hm);
					oos.close();
				}		
			}
			catch (Exception ex)
			{
				fail("Exception thrown during test: " + ex.toString());
			}
		}
		
		public void load() {
			try {
				FileInputStream fis;
				if (game.equals("Osprey")) {
					paused = true;
					fis = new FileInputStream("ospreyfile.ser");
					ObjectInputStream ois = new ObjectInputStream(fis);
					om = (OspreyModel) ois.readObject();
					ois.close();
					paused = false;
					System.out.println(om.getSeaweed().size());
				} else {
					paused = true;
					fis = new FileInputStream("harrierfile.ser");
					ObjectInputStream ois = new ObjectInputStream(fis);
					hm = (HarrierModel) ois.readObject();
					ois.close();
					paused = false;
				}		
			}
			catch (Exception ex)
			{
				fail("Exception thrown during test: " + ex.toString());
			}
		}

	}