
import static org.junit.Assert.*;

import org.junit.Test;

public class OspreyModelTest {

	@Test
	void Updatetest() {
		
		OspreyModel om = new OspreyModel();
		Osprey o = new Osprey();
		Fish f = new Fish();
		Seaweed s = new Seaweed();
		
		om.update();
		
		assertEquals(f.getXPos(), -1);
		assertEquals(o.getXPos(), 1);
		assertEquals(s.getXPos(), 0);
		
		
		fail("Not yet implemented");
	}
	
	@Test
	void checkInteractionsTest() {
		
		OspreyModel om = new OspreyModel();
		
		om.checkInteractions();

		assertEquals(om.equals(new OspreyModel()), true);
	}
	
	@Test
	void isEndTest() {
		
		OspreyModel om = new OspreyModel();
		
		assertEquals(om.isEnd(), false);
		
	}
	
	@Test
	void isWinTest() {
		
		OspreyModel om = new OspreyModel();
		
		
		assertEquals(om.isWin(), false);
		
	}

}
