
package Project;
import static org.junit.Assert.*;

import org.junit.Test;

public class HarrierModelTest {

	@Test
	public void testIsEnd() {
		HarrierModel hm = new HarrierModel();
		
		assertEquals(hm.isEnd(), false);
		hm.setTime(100000000);
		assertEquals(hm.isEnd(), true);
	}

	@Test
	public void testIsWin() {
		HarrierModel hm = new HarrierModel();
		
		assertEquals(hm.isWin(), false);
	
	}

	@Test
	public void testUpdate() {
		HarrierModel hm = new HarrierModel();
		Mouse m = new Mouse(0, 0);
		Tree t = new Tree(10, 10);
		
		hm.getMice().add(m);
		hm.getTrees().add(t);
		hm.update();
		
		assertEquals((int)m.getXPos(), 2);
		assertEquals((int)hm.getHarrier().getXPos(), 0);
		
	
	}
	
}