package Project;
import static org.junit.Assert.*;

import org.junit.Test;


public class OspreyModelTest {

	@Test
	public void testIsEnd() {
		OspreyModel om= new OspreyModel();
		
		om.getOsprey().setXVel(0);
		assertEquals(om.isEnd(), true);

	}

	@Test
	public void testIsWin() {
		OspreyModel om = new OspreyModel();
		
		om.getOsprey().setXPos(55000);
		
		assertEquals(om.isWin(), true);
	
	}

	
	@Test
	public void applyResistanceCheck() {
		OspreyModel om = new OspreyModel();
		
		om.getOsprey().setYPos(Osprey.MAX_HEIGHT);
		
		
		
		
	}
	
	
	
	
	@Test
	public void testUpdate() {
		
		OspreyModel om = new OspreyModel();
			
			Fish f = new Fish(3,0, 0);
			Seaweed s = new Seaweed(10, 10);
			int origFishArray=om.getFish().size();
			int origSeaweedArray=om.getSeaweed().size();
			
			
			om.getFish().add(f);
			om.getSeaweed().add(s);
			
			
			assertEquals(origFishArray+1, om.getFish().size());
			assertEquals(origSeaweedArray+1, om.getSeaweed().size());

		}
	}
		
		
		

