//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
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
	private Timer timerOsprey, timerHarrier;
	private int timerCount = 0;
	private final int timerUp = 1;
	private final int endTime = 401;
	private final int resetTime = 0;
	final static int TICK_TIME = 60;

	public Controller() { 
	
		tv = new TitleView();
		om = new OspreyModel();
		tv.addListener(this);
		paused = false; 
		timerCount = 0;
		ospreyAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!paused) {
					if(timerCount > endTime) {
						tv.c1.show(tv.contentPanel, "e1");
						timerOsprey.stop();
						timerCount = resetTime;
					}
					else {
					om.update();
					tv.ospreyUpdate(OspreyModel.getOsprey(), om.getFish(), om.getSeaweed());
					timerCount += timerUp;
					}
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
					if(timerCount > endTime) {
						tv.c1.show(tv.contentPanel, "e2");
						timerHarrier.stop();
						timerCount = resetTime;
					}
					else {
					hm.update();
					tv.harrierUpdate(HarrierModel.getHarrier(), hm.getFoxes(), hm.getMice(), hm.getTwigs(), hm.getTrees());
					timerCount += timerUp;
					}
				}
				else {
					try { Thread.sleep(TICK_TIME); }
					catch(InterruptedException e) { e.printStackTrace(); }
				}

			}

		};

	}
	
	/*
	 * This public method sets the string for game to menu, loads the main menu that holds both games
	 * Takes no parameters
	 * Returns nothing
	 */
	public void start() {
		game = "Title";
		om = null;
		ov = null;
		hm = null;
		hv = null;
	}
	
	/*
	 * This private method sets the string for the game to osprey, starts the timer to run the game
	 * Takes no parameters
	 * Returns nothing
	 */
	private void startOsprey() {
		game = "Osprey";
		om = new OspreyModel();
		timerOsprey = new Timer(TICK_TIME, ospreyAction);
		timerOsprey.start();
	}

	/*
	 * This private method sets the string for the game to harrier, starts the timer to run the game
	 * Takes no parameters
	 * Returns nothing
	 */
	private void startHarrier() {
		game = "Harrier";
		hm = new HarrierModel();
		timerHarrier = new Timer(TICK_TIME, harrierAction);
		timerHarrier.start();
	}

	/* 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (Controller.game.equals("Osprey")) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) { OspreyModel.getOsprey().dive(); }
			if(e.getKeyCode() == KeyEvent.VK_P) { Controller.paused = !paused;}
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				tv.c1.show(tv.contentPanel, "m");
				timerOsprey.stop();
				start();
			}
		}

		else if(Controller.game.contentEquals("Harrier")) {
				if (e.getKeyCode() == KeyEvent.VK_UP) { HarrierModel.getHarrier().goNorth(); }
				if (e.getKeyCode() == KeyEvent.VK_DOWN) { HarrierModel.getHarrier().goSouth(); }
				if (e.getKeyCode() == KeyEvent.VK_LEFT) { HarrierModel.getHarrier().goWest(); }
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) { HarrierModel.getHarrier().goEast(); }
				if(e.getKeyCode() == KeyEvent.VK_P) { Controller.paused = !paused;}
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					tv.c1.show(tv.contentPanel, "m");
					timerHarrier.stop();
					start();
				}
			}
		else if(Controller.game.contentEquals("Title")) {
			if(e.getKeyCode() == KeyEvent.VK_1) {
				tv.game1.doClick();
			}
			if(e.getKeyCode() == KeyEvent.VK_2) {
				tv.game2.doClick();
			}
		}
		else {
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				tv.c1.show(tv.contentPanel, "m");
				start();
			}
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
		}

		/*
		 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
		 */
		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton src = (JButton) e.getSource();
			if (src.equals(tv.game1)) {  tv.c1.show(tv.contentPanel, "o"); startOsprey(); }
			else if (src.equals(tv.game2)) { tv.c1.show(tv.contentPanel, "h"); startHarrier(); }
		}
		
		/*
		 * Starts the program
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					Controller c = new Controller();
					c.start();
				}
			});

		}

	}