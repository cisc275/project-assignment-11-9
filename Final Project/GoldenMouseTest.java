package Project;

import static org.junit.Assert.*;

import org.junit.Test;

public class GoldenMouseTest {

	@Test
	void testInteractHarrier() {
		Harrier h= new Harrier();
		
		GoldenMouse gm=new GoldenMouse();
		
		gm.interact(h);
		
		assertEquals(h.getScore(), 5);
		
		
		
		fail("Not yet implemented");
	}
}
