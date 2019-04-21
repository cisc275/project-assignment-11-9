package Project;

import static org.junit.Assert.*;

import org.junit.Test;

public class HarrierTest {

	@Test
	public void testMove() {
		
		Harrier h = new Harrier();
		h.goNorth();
		h.goSouth();
		h.goEast();
		h.goWest();
		
		

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

