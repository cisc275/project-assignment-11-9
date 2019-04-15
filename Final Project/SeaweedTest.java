
import static org.junit.Assert.*;

import org.junit.Test;

public class SeaweedTest {

	@Test
	void Slowdowntest() {
		
		Osprey o = new Osprey();
		Seaweed s = new Seaweed();
		
		s.interact(o);
		
		assertEquals(o.getXVel(),-1);
		
		
		fail("Not yet implemented");
	}

}
