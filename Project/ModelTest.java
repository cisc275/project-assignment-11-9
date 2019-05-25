package Project;

import static org.junit.Assert.*;

import org.junit.Test;


public class ModelTest {

	@Test
	void testCollisions() {
		Model m = new HarrierModel();
			
			Harrier h = new Harrier();
			Mouse mo = new Mouse(2,2);
			
		
			assertEquals(m.isCollision(h, mo), true);
			
			
			
		
	}
	
	
	
	@Test
	public void timeTest() {
		Model m = new HarrierModel();
		
		m.setTime(1);
		assertEquals(m.getTime(), 1);
	}
	
}