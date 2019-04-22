package Project;

import static org.junit.Assert.*;

import org.junit.Test;

public class AnimalTest {

	@Test
	public void testInteract() {
		Animal a = new Fish(0, 0, 1);
		GameObject g = new Fish(0, 0, 1);
		double xVel = a.getXVel();
		double yVel = a.getYVel();
		a.interact(g);
		
		assertEquals(a.getXVel(), -1 * xVel, .00001);
		assertEquals(a.getYVel(), -1 * yVel, .00001);
	}
	
	@Test
	public void testMove() {
		Animal a = new Fish(0, 0, 1);
		double xPos = a.getXPos();
		double yPos = a.getYPos();
		double xVel = a.getXVel();
		double yVel = a.getYVel();
		a.move();
		
		assertEquals(a.getXPos(), xPos + xVel, .00001);
		assertEquals(a.getYPos(), yPos + yVel, .00001);
	}
	
	@Test
	public void testUpdateDirection() {
		Animal a = new Fish(0, 0, 1);
		
		a.setXVel(1);
		a.setYVel(1);
		a.updateDirection();
		assertEquals(a.getDirection(), Direction.SOUTHEAST);
		
		a.setYVel(0);
		a.updateDirection();
		assertEquals(a.getDirection(), Direction.EAST);
		
		a.setYVel(-1);
		a.updateDirection();
		assertEquals(a.getDirection(), Direction.NORTHEAST);
		
		a.setXVel(-1);
		a.setYVel(1);
		a.updateDirection();
		assertEquals(a.getDirection(), Direction.SOUTHWEST);
		
		a.setYVel(0);
		a.updateDirection();
		assertEquals(a.getDirection(), Direction.WEST);
		
		a.setYVel(-1);
		a.updateDirection();
		assertEquals(a.getDirection(), Direction.NORTHWEST);
		
		a.setXVel(0);
		a.setYVel(1);
		a.updateDirection();
		assertEquals(a.getDirection(), Direction.SOUTH);
		
		a.setYVel(0);
		a.updateDirection();
		assertEquals(a.getDirection(), Direction.SOUTH);
		
		a.setYVel(-1);
		a.updateDirection();
		assertEquals(a.getDirection(), Direction.NORTH);
	}

}
