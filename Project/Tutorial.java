package Project;

public enum Tutorial {
	
	ONE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	NONE(0);

	private int stage = -1;

	private Tutorial(int stage){ this.stage = stage; }

	public int getStage() { return stage; }

}
