//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

/*
 * public enum HarrierQuestion defines the 5 questions a player could be asked in a Harrier game quiz.
 */
public enum HarrierQuestion {
	ZERO("Press one of the 'a', 'b', or 'c' keys to proceed", "a", "b", "c"),
	ONE("What do Harriers eat?", "mice", "badgers", "leaves"),
	TWO("What animals hunt Harriers?", "foxes", "bears", "cougars"),
	THREE("What do Harriers use to build their nests?", "twigs", "plastic bottles", "dead animals"),
	FOUR("How do Harriers move around?", "Stay in one general area", "Migrate between continents", "Sometimes migrate, sometimes not");
	private String question;
	private String answers[] = new String[3];
	private HarrierQuestion(String q, String a1, String a2, String a3) {
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
