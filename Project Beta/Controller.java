 //Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/* 
 * Public Controller class runs the game.
 */
public class Controller implements ActionListener, KeyListener {

	private GameState gs;
	private Model model;
	private JPanel view;
	private JFrame frame;
	private CardLayout layout;
	private Timer timerO, timerH;
	private boolean paused;
	public final static int TICK_TIME = 20;
	
	public Controller() {
		view = new JPanel();
		layout = new CardLayout();
		frame = new JFrame();
		
		view.setLayout(layout);
		view.add(new TitleView(), "tv", GameState.TITLE.getNum());
		view.add(new OspreyView(), "ov", GameState.OSPREY.getNum());
		view.add(new HarrierView(), "hv", GameState.HARRIER.getNum());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT);
		frame.add(view);
		frame.setVisible(true);
		frame.addKeyListener(this);
		((TitleView)view.getComponent(GameState.TITLE.getNum())).addListener(this);
		
		timerO = new Timer(TICK_TIME, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if(!paused) {
					OspreyModel om = (OspreyModel)model;
					OspreyView ov = (OspreyView)view.getComponent(gs.getNum());
					om.update();
					ov.update(om.getOsprey(), om.getFish(), om.getSeaweed());
					frame.repaint();
				}
				else {
					try { Thread.sleep(TICK_TIME); }
					catch(InterruptedException e) { e.printStackTrace(); }
				}
			}
		});
		timerH = new Timer(TICK_TIME, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if(!paused) {
					HarrierModel hm = (HarrierModel)model;
					HarrierView hv = (HarrierView)view.getComponent(gs.getNum());
					hm.update();
					hv.update(hm.getHarrier(), hm.getFoxes(), hm.getMice(), hm.getTwigs(), hm.getTrees());
					frame.repaint();
				}
				else {
					try { Thread.sleep(TICK_TIME); }
					catch(InterruptedException e) { e.printStackTrace(); }
				}
			}
		});
	}
	
	/*
	 * public method start.
	 * Takes no parameters and returns nothing.
	 * Opens the title screen.
	 */
	public void start() {
		gs = GameState.TITLE;
		model = null;
		layout.show(view, "tv");
		paused = false;
	}

	/*
	 * public method startOsprey.
	 * Takes no parameters and returns nothing.
	 * Runs the game that involves the osprey.
	 */
	public void startOsprey() {
		gs = GameState.OSPREY;
		model = new OspreyModel();
		layout.show(view, "ov");
		frame.requestFocus();
		timerO.start();
	}
	
	/*
	 * public method startOsprey.
	 * Takes no parameters and returns nothing.
	 * Runs the game that involves the harrier.
	 */
	public void startHarrier() {
		gs = GameState.HARRIER;
		model = new HarrierModel();
		layout.show(view, "hv");
		frame.requestFocus();
		timerH.start();
	}

	/* 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if(gs != GameState.TITLE) {
			if (gs == GameState.OSPREY) {
				OspreyModel om = (OspreyModel)model;
				if (e.getKeyCode() == KeyEvent.VK_SPACE && !om.getOsprey().getIsRecovering()) { om.getOsprey().dive(); }
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) { timerO.stop(); start(); }
			}
			else if(gs == GameState.HARRIER) {
				HarrierModel hm = (HarrierModel)model;
				if (e.getKeyCode() == KeyEvent.VK_UP) { hm.getHarrier().goNorth(); }
				if (e.getKeyCode() == KeyEvent.VK_DOWN) { hm.getHarrier().goSouth(); }
				if (e.getKeyCode() == KeyEvent.VK_LEFT) { hm.getHarrier().goWest(); }
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) { hm.getHarrier().goEast(); }
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) { timerH.stop(); start(); }
			}
			if (e.getKeyCode() == KeyEvent.VK_CONTROL) { GameView gv = (GameView)view.getComponent(gs.getNum()); gv.setIsDebug(!gv.getIsDebug()); }
			if (e.getKeyCode() == KeyEvent.VK_P) { paused = !paused; }
		}
	}

	/* 
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if (gs == GameState.OSPREY) {
			OspreyModel om = (OspreyModel)model;
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
		TitleView tv = (TitleView)view.getComponent(GameState.TITLE.getNum());
		if (src.equals(tv.getButtonO())) { startOsprey(); }
		else if (src.equals(tv.getButtonH())) { startHarrier(); }
	}

	public static void main(String[] args) {
		Controller c = new Controller();
		c.start();
	}

}
