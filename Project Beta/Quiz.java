//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JLabel;

/*
 * Public class Quiz defines the behavior and attributes of the Quizzes in both games.
 */
public class Quiz extends JDialog implements java.io.Serializable {
	
	private JPanel panel;
	private Random rand = new Random();
	private String[] answers = new String[3];
	String answer;
	private String[] orderedAnswers;
	
	public Quiz(GoldenFish f) {
		OspreyQuestion questionInfo = null;
		this.setModal(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setSize(300, 300);
		this.setTitle("Golden Fish quiz");
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		if (f.tutorial) {
			questionInfo = OspreyQuestion.ZERO;
			answers = questionInfo.getAnswers();
			orderedAnswers = questionInfo.getAnswers();
		} else {
			switch(rand.nextInt(4)) {
			case 0:
				questionInfo = OspreyQuestion.ONE;
				break;
			case 1:
				questionInfo = OspreyQuestion.TWO;
				break;
			case 2:
				questionInfo = OspreyQuestion.THREE;
				break;
			case 3:
				questionInfo = OspreyQuestion.FOUR;
				break;
			}
			orderedAnswers = questionInfo.getAnswers();
			switch(rand.nextInt(6)) {
			case 0:
				answers = questionInfo.getAnswers();
				break;
			case 1:
				answers[0] = orderedAnswers[0];
				answers[1] = orderedAnswers[2];
				answers[2] = orderedAnswers[1];
				break;
			case 2:
				answers[0] = orderedAnswers[1];
				answers[1] = orderedAnswers[0];
				answers[2] = orderedAnswers[2];
				break;
			case 3:
				answers[0] = orderedAnswers[1];
				answers[1] = orderedAnswers[2];
				answers[2] = orderedAnswers[0];
				break;
			case 4:
				answers[0] = orderedAnswers[2];
				answers[1] = orderedAnswers[0];
				answers[2] = orderedAnswers[1];
				break;
			case 5:
				answers[0] = orderedAnswers[2];
				answers[1] = orderedAnswers[1];
				answers[2] = orderedAnswers[0];
				break;
			}
		}
		JLabel question = new JLabel(questionInfo.getQuestion());
		panel.add(question, "cell 0 1,alignx trailing,aligny center");
		
		JLabel answer1 = new JLabel("a - " + answers[0]);
		panel.add(answer1, "cell 1, 2,alignx center,aligny center");
		
		JLabel answer2 = new JLabel("b - " + answers[1]);
		panel.add(answer2, "cell 2, 1,alignx center,aligny center");
		
		JLabel answer3 = new JLabel("c - " + answers[2]);
		panel.add(answer3, "cell 3, 1,alignx center,aligny center");
		
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			};
			
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_A:
					answer = answers[0];
					if (answer.equals(orderedAnswers[0]) || f.tutorial) {
						f.correct = true;
					}
					dispose();
					break;
				case KeyEvent.VK_B:
					answer = answers[1];
					if (answer.equals(orderedAnswers[0]) || f.tutorial) {
						f.correct = true;
					}
					dispose();
					break;
				case KeyEvent.VK_C:
					answer = answers[2];
					if (answer.equals(orderedAnswers[0]) || f.tutorial) {
						f.correct = true;
					}
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
		HarrierQuestion questionInfo = null;
		this.setModal(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setSize(300, 300);
		this.setTitle("Golden Fish quiz");
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		if (m.tutorial) {
			questionInfo = HarrierQuestion.ZERO;
			answers = questionInfo.getAnswers();
			orderedAnswers = questionInfo.getAnswers();
		} else {
			switch(rand.nextInt(4)) {
			case 0:
				questionInfo = HarrierQuestion.ONE;
				break;
			case 1:
				questionInfo = HarrierQuestion.TWO;
				break;
			case 2:
				questionInfo = HarrierQuestion.THREE;
				break;
			case 3:
				questionInfo = HarrierQuestion.FOUR;
				break;
			}
			orderedAnswers = questionInfo.getAnswers();
			switch(rand.nextInt(6)) {
			case 0:
				answers = questionInfo.getAnswers();
				break;
			case 1:
				answers[0] = orderedAnswers[0];
				answers[1] = orderedAnswers[2];
				answers[2] = orderedAnswers[1];
				break;
			case 2:
				answers[0] = orderedAnswers[1];
				answers[1] = orderedAnswers[0];
				answers[2] = orderedAnswers[2];
				break;
			case 3:
				answers[0] = orderedAnswers[1];
				answers[1] = orderedAnswers[2];
				answers[2] = orderedAnswers[0];
				break;
			case 4:
				answers[0] = orderedAnswers[2];
				answers[1] = orderedAnswers[0];
				answers[2] = orderedAnswers[1];
				break;
			case 5:
				answers[0] = orderedAnswers[2];
				answers[1] = orderedAnswers[1];
				answers[2] = orderedAnswers[0];
				break;
			}
		}
		JLabel question = new JLabel(questionInfo.getQuestion());
		panel.add(question, "cell 0 1,alignx trailing,aligny center");
		
		JLabel answer1 = new JLabel("a - " + answers[0]);
		panel.add(answer1, "cell 1, 2,alignx center,aligny center");
		
		JLabel answer2 = new JLabel("b - " + answers[1]);
		panel.add(answer2, "cell 2, 1,alignx center,aligny center");
		
		JLabel answer3 = new JLabel("c - " + answers[2]);
		panel.add(answer3, "cell 3, 1,alignx center,aligny center");
		
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			};
			
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_A:
					answer = answers[0];
					if (answer.equals(orderedAnswers[0]) || m.tutorial) {
						m.correct = true;
					}
					dispose();
					break;
				case KeyEvent.VK_B:
					answer = answers[1];
					if (answer.equals(orderedAnswers[0]) || m.tutorial) {
						m.correct = true;
					}
					dispose();
					break;
				case KeyEvent.VK_C:
					answer = answers[2];
					if (answer.equals(orderedAnswers[0]) || m.tutorial) {
						m.correct = true;
					}
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