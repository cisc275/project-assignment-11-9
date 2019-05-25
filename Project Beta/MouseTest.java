package Project;

import static org.junit.Assert.*;

import org.junit.Test;

public class MouseTest {

	
	
	
	@Test
	public void testInteractHarrier() {
		Mouse m = new Mouse(0, 0);
		Harrier h = new Harrier();
		

			
		
		m.interact(h);
		
		assertEquals(h.getScore(), 10);
		assertEquals(1, h.getVision(), 0.01);
		
		h.setVision(75);
		m.interact(h);
		
		assertEquals((int)h.getVision(),100);
		
		h.setVision(700);
		m.interact(h);
		assertEquals((int)h.getVision(), (int)Harrier.MAX_VISION);
		
		
		
		
		

}
}
