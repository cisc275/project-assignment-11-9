 //Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;

/* 
 * Public Controller class runs the game.
 */
public class Controller implements ActionListener, KeyListener {

	private GameState gs;
	private TitleView tv;
	private Model m;
	private GameView gv;
	private boolean paused;
	final static int TICK_TIME = 20;
	
	/*
	 * public method start.
	 * Takes no parameters and returns nothing.
	 * Opens the title screen.
	 */
	public void start() {
		gs = GameState.TITLE;
		tv = new TitleView();
		tv.addListener(this);
		m = null;
		gv = null;
	}

	/*
	 * public method startOsprey.
	 * Takes no parameters and returns nothing.
	 * Runs the game that involves the osprey.
	 */
	public void startOsprey() {
		tv.dispose();
		gs = GameState.OSPREY;
		m = new OspreyModel();
		gv = new OspreyView();
		gv.addListener(this);
		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				if(!paused) {
					OspreyModel om = (OspreyModel)m;
					OspreyView ov = (OspreyView)gv;
					om.update();
					ov.update(om.getOsprey(), om.getFish(), om.getSeaweed());
				}
				else {
					try { Thread.sleep(TICK_TIME); }
					catch(InterruptedException e) { e.printStackTrace(); }
				}
			}
		};
		timer.schedule(timerTask, 0, TICK_TIME);
	}
	
	/*
	 * public method startOsprey.
	 * Takes no parameters and returns nothing.
	 * Runs the game that involves the harrier.
	 */
	public void startHarrier() {
		tv.dispose();
		gs = GameState.HARRIER;
		m = new HarrierModel();
		gv = new HarrierView();
		gv.addListener(this);
		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				if(!paused) {
					HarrierModel hm = (HarrierModel)m;
					HarrierView hv = (HarrierView)gv;
					hm.update();
					hv.update(hm.getHarrier(), hm.getFoxes(), hm.getMice(), hm.getTwigs(), hm.getTrees());
				}
				else {
					try { Thread.sleep(TICK_TIME); }
					catch(InterruptedException e) { e.printStackTrace(); }
				}
			}
		};
		timer.schedule(timerTask, 0, TICK_TIME);
	}

	/* 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (gs == GameState.OSPREY) {
			OspreyModel om = (OspreyModel)m;
			if (e.getKeyCode() == KeyEvent.VK_SPACE && !om.getOsprey().getIsRecovering()) { om.getOsprey().dive(); }
		}
		else if(gs == GameState.HARRIER) {
			HarrierModel hm = (HarrierModel)m;
			if (e.getKeyCode() == KeyEvent.VK_UP) { hm.getHarrier().goNorth(); }
			if (e.getKeyCode() == KeyEvent.VK_DOWN) { hm.getHarrier().goSouth(); }
			if (e.getKeyCode() == KeyEvent.VK_LEFT) { hm.getHarrier().goWest(); }
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) { hm.getHarrier().goEast(); }
		}
		if (e.getKeyCode() == KeyEvent.VK_CONTROL) { gv.setIsDebug(!gv.getIsDebug()); }
		if (e.getKeyCode() == KeyEvent.VK_P) { paused = !paused; }
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) { gs = GameState.TITLE; gv.frame.dispose(); start(); paused = false; }
	}

	/* 
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if (gs == GameState.OSPREY) {
			OspreyModel om = (OspreyModel)m;
			if (e.getKeyCode() == KeyEvent.VK_SPACE) { om.getOsprey().rise(); }
		}
	}

	/*
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {}

	/*
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton src = (JButton) e.getSource();
		if (src.equals(tv.getButtonO())) { startOsprey(); }
		else if (src.equals(tv.getButtonH())) { startHarrier(); }
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

}
