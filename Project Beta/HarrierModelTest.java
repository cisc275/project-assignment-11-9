package Project;
import static org.junit.Assert.*;

import org.junit.Test;

public class HarrierModelTest {

	@Test
	public void testIsEnd() {
		HarrierModel hm = new HarrierModel();
		hm.getHarrier().setVision(30);
		assertEquals(hm.isEnd(), false);
		hm.getHarrier().setVision(0);
		assertEquals(hm.isEnd(), true);
	}

	@Test
	public void testIsWin() {
		HarrierModel hm = new HarrierModel();
		hm.setTime(100000000);
		assertEquals(hm.isWin(), true);
	
	}
	@Test
	public void testUpdate() {
		HarrierModel hm = new HarrierModel();
		Mouse m = new Mouse(0, 0);
		Tree t = new Tree(10, 10);
		int origMouseArray=hm.getMice().size();
		int origTreeArray=hm.getTrees().size();
		
		
		hm.getMice().add(m);
		hm.getTrees().add(t);
		
		
		assertEquals(origMouseArray+1, hm.getMice().size());
		assertEquals(origTreeArray+1, hm.getTrees().size());

	}
}

	

