//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
/*
 * public enum OspreyQuestion defines the 5 questions a player could be asked in a Osprey game quiz.
 */
public enum OspreyQuestion {
	ZERO("Press one of the 'a', 'b', or 'c' keys to proceed", "a", "b", "c"),
	ONE("What do Ospreys eat?", "fish", "garbage", "seaweed"),
	TWO("Where do Ospreys migrate from?", "South America", "Africa", "Asia"),
	THREE("What route do Ospreys take in migration?", "Along the coast", "Straight across the ocean", "Through the mountains"),
	FOUR("Where do Ospreys make their nests?", "Delaware", "Colorado", "West Texas");
	
	private String question;
	private String[] answers = new String[3];
	private OspreyQuestion(String q, String a1, String a2, String a3) {
		question = q; 
		answers[0] = a1;
		answers[1] = a2;
		answers[2] = a3;
	}
	
	public String getQuestion() {
		return question;
	}
	public String[] getAnswers() {
		return answers;
	}
}
