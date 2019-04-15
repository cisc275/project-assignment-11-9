//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

/*
 * Public abstract class Animal defines general behaviors and attributes for movable objects.
 */
public abstract class Animal extends GameObject {

	private int xVelocity;
	private int yVelocity;
	private Direction direction;

	public Animal() {
		super();
		xVelocity = 0;
		yVelocity = 0;
		direction = Direction.SOUTH;
	}

	public int getXVel() { return this.xVelocity; }

	public void setXVel(int xVel) { this.xVelocity = xVel; }

	public int getYVel() { return this.yVelocity; }

	public void setYVel(int yVel) { this.yVelocity = yVel; }
	
	public Direction getDirection() { return this.direction; }
	
	public void setDirection(Direction direction) { this.direction = direction; }

	/*
	 * public method interact.
	 * Takes GameObject as parameter and returns nothing.
	 * Processes an interaction between the Animal and the GameObject, a general ricochet.
	 */
	public void interact(GameObject g) {
		this.setXVel(-1 * this.xVelocity);
		this.setYVel(-1 * this.yVelocity);
	}
	
	/*
	 * public method move.
	 * Takes no parameters and returns nothing.
	 * Increments the positions by their respective velocity.
	 */
	public void move() {
		setXPos(getXPos() + xVelocity);
		setYPos(getYPos() + yVelocity);
	}

	/*
	 * public method updateDirection.
	 * Takes no parameters and returns nothing.
	 * Updates the direction of the animal by checking signs of velocities.
	 */
	public void updateDirection() {
		if(xVelocity < 0) {
			if(yVelocity < 0) { direction = Direction.NORTHWEST; }
			else if(yVelocity > 0) { direction = Direction.SOUTHWEST; }
			else { direction = Direction.WEST; }
		}
		else if(xVelocity > 0) {
			if(yVelocity < 0) { direction = Direction.NORTHEAST; }
			else if(yVelocity > 0) { direction = Direction.SOUTHEAST; }
			else { direction = Direction.EAST; }
		}
		else {
			if( yVelocity < 0) { direction = Direction.NORTH; }
			else { direction = Direction.SOUTH; }
		}
	}
}
