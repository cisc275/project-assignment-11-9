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
		

}
}
