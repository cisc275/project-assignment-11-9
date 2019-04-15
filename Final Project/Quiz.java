//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

import java.util.*;

/*
 * Public class Quiz defines the behavior and attributes of the Quizzes in each game.
 */
public class Quiz {
	
	ArrayList<String> questions;
	ArrayList<String> answers;
	
	/*
	 * public method isRight.
	 * Takes no parameters, and returns a boolean value 
	 * reflecting if the correct answer was chosen.
	 */
	public boolean isRight() {
		return false;
	}
	
	public ArrayList<String> getQuestions() { return this.questions; }
	
	public void setQuestions(ArrayList<String> questions) { this.questions = questions; }
	
	public ArrayList<String> getAnswers() { return this.answers; }
	
	public void setAnswers(ArrayList<String> answers) { this.answers = answers; }
}
