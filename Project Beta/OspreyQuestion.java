package Project;
public enum OspreyQuestion {
	ZERO("Press one of the 'a', 'b', or 'c' keys to proceed", "a", "b", "c"),
	ONE("What do Ospreys eat?", "fish", "garbage", "seaweed"),
	TWO("Where do Ospreys migrate from?", "South America", "Africa", "Asia"),
	THREE("What route do Ospreys take in migration?", "Along the coast", "Straight across the ocean", "Through the mountains"),
	FOUR("Where do Ospreys make their nests?", "Delaware", "Colorado", "West Texas");
	
	private String question;
	private String answer1;
	private String answer2;
	private String answer3;
	private OspreyQuestion(String q, String a1, String a2, String a3) {
		question = q; 
		answer1 = a1;
		answer2 = a2; 
		answer3 = a3;
	}
	
	public String getQuestion() {
		return question;
	}
	public String[] getAnswers() {
		String[] answers = {answer1, answer2, answer3};
		return answers;
	}
}
