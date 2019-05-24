//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
/*
 * public enum Direction defines the 8 directions that the objects in the Harrier game can travel
 */
public enum Direction {

	NORTH("north"),
	NORTHEAST("northeast"),
	EAST("east"),
	SOUTHEAST("southeast"),
	SOUTH("south"),
	SOUTHWEST("southwest"),
	WEST("west"),
	NORTHWEST("northwest");

	private String name = null;

	private Direction(String s){ name = s; }

	public String getName() { return name; }

}