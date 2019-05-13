//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;

/*
 * Public class Quiz defines the behavior and attributes of the Quizzes in both games.
 */
public class Quiz extends JDialog {
	
	private JPanel panel;
	
	private String answer = "0";
	
	public Quiz(GoldenFish f) {
		this.setModal(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setSize(300, 300);
		this.setTitle("Golden animal quiz");
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		JLabel question = new JLabel("What do Ospreys eat?");
		panel.add(question, "cell 0 1,alignx trailing,aligny center");
		
		JLabel answer1 = new JLabel("a - Fish");
		panel.add(answer1, "cell 1, 1,alignx center,aligny center");
		
		JLabel answer2 = new JLabel("b - Trash");
		panel.add(answer2, "cell 2, 1,alignx center,aligny center");
		
		JLabel answer3 = new JLabel("c - Seaweed");
		panel.add(answer3, "cell 3, 1,alignx center,aligny center");
		
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			};
			
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_A:
					answer = "a";
				case KeyEvent.VK_B:
					if (answer == "0") {
						answer = "b";
					}
				case KeyEvent.VK_C:
					if (answer == "0") {
						answer = "c";
					}
					System.out.println(answer);
					f.answer = answer;
					f.correct = "a";
					dispose();
					break;
				default:
					break;
				}
			};
			
			@Override
			public void keyReleased(KeyEvent e) {};
		});
		
		this.setVisible(true);
		
	}
	
	public Quiz(GoldenMouse m) {
		this.setModal(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setSize(300, 300);
		this.setTitle("Golden animal quiz");
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		JLabel question = new JLabel("What do Ospreys eat?");
		panel.add(question, "cell 0 1,alignx trailing,aligny center");
		
		JLabel answer1 = new JLabel("a - Fish");
		panel.add(answer1, "cell 1, 1,alignx center,aligny center");
		
		JLabel answer2 = new JLabel("b - Trash");
		panel.add(answer2, "cell 2, 1,alignx center,aligny center");
		
		JLabel answer3 = new JLabel("c - Seaweed");
		panel.add(answer3, "cell 3, 1,alignx center,aligny center");
		
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			};
			
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_A:
					answer = "a";
				case KeyEvent.VK_B:
					if (answer == "0") {
						answer = "b";
					}
				case KeyEvent.VK_C:
					if (answer == "0") {
						answer = "c";
					}
					System.out.println(answer);
					m.answer = answer;
					m.correct = "a";
					dispose();
					break;
				default:
					break;
				}
			};
			
			@Override
			public void keyReleased(KeyEvent e) {};
		});
		
		this.setVisible(true);
		
	}
	//String[] questions;
	//char[] answerKey;

	/*
	 * public method isRight.
	 * Takes int and char as parameter, and returns a boolean value 
	 * reflecting if the correct answer was chosen for the given question.
	 */
	/*public boolean isRight(int question, char answer) {
		return answer == answerKey[question];
	}
	
	public String[] getQuestions() { return this.questions; }
	
	public char[] getAnswers() { return this.answerKey; }
	
	public String getInput() { return answer; }*/

}
