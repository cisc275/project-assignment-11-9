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
	private OspreyModel om;
	private OspreyView ov;
	private HarrierModel hm;
	private HarrierView hv;
	private boolean Paused = false; 
	final static int TICK_TIME = 50;

	public void start() {
		gs = GameState.TITLE;
		tv = new TitleView();
		tv.addListener(this);
	}

	public void startOsprey() {
		tv.dispose();
		gs = GameState.OSPREY;
		om = new OspreyModel();
		ov = new OspreyView();
		ov.addListener(this);
		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				if(!Paused) {
					om.update();
					ov.update(om.getOsprey(), om.getFish(), om.getSeaweed());
				}
				else {
					try {
						Thread.sleep(1);
					}
					catch(InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
		};
		timer.schedule(timerTask, 0, TICK_TIME);
	}

	public void startHarrier() {
		tv.dispose();
		gs = GameState.HARRIER;
		hm = new HarrierModel();
		hv = new HarrierView();
		hv.addListener(this);
		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				if(!Paused) {
					hm.update();
					hv.update(hm.getHarrier(), hm.getFoxes(), hm.getMice(), hm.getTwigs(), hm.getTrees());
				}
				else {
					try {
						Thread.sleep(1);
					}
					catch(InterruptedException e1) {
						e1.printStackTrace();
					}
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
			if (e.getKeyCode() == KeyEvent.VK_SPACE) { om.getOsprey().dive(); }
			if (e.getKeyCode() == KeyEvent.VK_P) { Paused = !Paused;}
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) { gs = GameState.TITLE; ov.frame.dispose(); start();}
		}
		else if(gs == GameState.HARRIER) {
			if (e.getKeyCode() == KeyEvent.VK_UP) { hm.getHarrier().goNorth(); }
			if (e.getKeyCode() == KeyEvent.VK_DOWN) { hm.getHarrier().goSouth(); }
			if (e.getKeyCode() == KeyEvent.VK_LEFT) { hm.getHarrier().goWest(); }
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) { hm.getHarrier().goEast(); }
			if (e.getKeyCode() == KeyEvent.VK_P) { Paused = !Paused;}
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {gs = GameState.TITLE; hv.frame.dispose(); start();}
		}
	}

	/* 
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if (gs == GameState.OSPREY) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) { om.getOsprey().rise(); }
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
		if (src.equals(tv.getButtonO())) { startOsprey(); }
		else if (src.equals(tv.getButtonH())) { startHarrier(); }
	}

	public static void main(String[] args) {
		Controller c = new Controller();
		c.start();
	}

}
