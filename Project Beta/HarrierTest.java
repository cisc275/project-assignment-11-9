package Project;

import static org.junit.Assert.*;

import org.junit.Test;

public class HarrierTest {

	@Test
	public void testMoveNorth() {
		
		Harrier h = new Harrier();
		h.goNorth();
		assertEquals(-3,h.getYVel(), 0.01);
		
		

	}
	
	@Test
	public void testMoveSouth() {
		
		Harrier h = new Harrier();
		h.goSouth();
		assertEquals(3,h.getYVel(), 0.01);
		
	}
	
	@Test
	public void testMoveEast() {
		
		Harrier h = new Harrier();
		h.goEast();
		assertEquals(3,h.getXVel(), 0.01);
		
		

	}
	
	@Test
	public void testMoveWest() {
		
		Harrier h = new Harrier();
		h.goWest();
		assertEquals(-3,h.getXVel(), 0.01);
		
		

	}
	
	@Test
	public void testScore() {
		Harrier h = new Harrier();
		h.setScore(1);
		assertEquals(h.getScore(), 1);
	
		
	}
	
	@Test
	public void testVision() {
		Harrier h = new Harrier();
		h.setVision(10);
		assertEquals(10, h.getVision(), 0.01);
		
	}

}

