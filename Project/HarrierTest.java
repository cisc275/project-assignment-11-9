package Project;

import static org.junit.Assert.*;

import org.junit.Test;

public class HarrierTest {

	@Test
	public void testMoveNorth() {
		
		Harrier h = new Harrier();
		h.goNorth();
		assertEquals((int)h.getXVel(), 0);
		
		

	}
	
	@Test
	public void testMoveSouth() {
		
		Harrier h = new Harrier();
		h.goSouth();
		assertEquals((int)h.getXVel(), 0);
		
	}
	
	@Test
	public void testMoveEast() {
		
		Harrier h = new Harrier();
		h.goEast();
		assertEquals((int)h.getYVel(), 0);
		
		

	}
	
	@Test
	public void testMoveWest() {
		
		
		Harrier h = new Harrier();
		h.goWest();
		assertEquals((int)h.getYVel(), 0);
		
		

	}
	
	@Test
	public void testScore() {
		Harrier h = new Harrier();
		h.setScore(1);
		assertEquals(h.getScore(), 1);
	
		
	}
	@Test
	public void testDie() {
		Harrier h = new Harrier();
		h.reset();
		assertEquals((int)h.getXPos(),0);
		assertEquals((int)h.getYPos(),0);
	}
	
	@Test
	public void testVision() {
		Harrier h = new Harrier();
		h.setVision(10);
		assertEquals(10, h.getVision(), 0.01);
		
	}
	
}
