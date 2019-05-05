//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;

/*
 * Public class Quiz defines the behavior and attributes of the Quizzes in both games.
 */
public class Quiz {
	
	String[] questions;
	char[] answerKey;

	/*
	 * public method isRight.
	 * Takes int and char as parameter, and returns a boolean value 
	 * reflecting if the correct answer was chosen for the given question.
	 */
	public boolean isRight(int question, char answer) {
		return answer == answerKey[question];
	}
	
	public String[] getQuestions() { return this.questions; }
	
	public char[] getAnswers() { return this.answerKey; }

}
