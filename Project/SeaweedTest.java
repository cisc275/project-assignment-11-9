package Project;
import static org.junit.Assert.*;

import org.junit.Test;


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
		o.setXVel(10);
		assertEquals((int)o.getXVel(), 9);
		
		
	}

}