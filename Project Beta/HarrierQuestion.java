package Project;
public enum HarrierQuestion {
	ZERO("Press one of the 'a', 'b', or 'c' keys to proceed", "a", "b", "c"),
	ONE("What do Harriers eat?", "mice", "badgers", "leaves"),
	TWO("What animals hunt Harriers?", "foxes", "bears", "cougars"),
	THREE("What do Harriers use to build their nests?", "twigs", "plastic bottles", "dead animals"),
	FOUR("How do Harriers move around?", "Stay in one general area", "Migrate between continents", "Sometimes migrate, sometimes not");
	private String question;
	private String answer1;
	private String answer2;
	private String answer3;
	private HarrierQuestion(String q, String a1, String a2, String a3) {
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
