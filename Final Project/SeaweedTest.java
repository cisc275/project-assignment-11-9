package Project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SeaweedTest {

	@Test
	void Interacttest() {
		
		Osprey o= new Osprey();
		
		Seaweed s = new Seaweed(0,0);
		
		s.interact(o);
		
		
		assertEquals((int) s.getXPos(), 0);
		
		assertEquals((int) s.getYPos(), 0);
		
		s.setXPos(5);
		s.setYPos(5);
		
		assertEquals((int) s.getXPos(), 5);
		
		assertEquals((int) s.getYPos(), 5);
		
		s.interact(o);
		

		
		
		
		
		fail("Not yet implemented");
	}

}
