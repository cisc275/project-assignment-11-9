package Project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ControllerTest {

	@Test
	public void testStart() {
		Controller c = new Controller();
		c.start();
	}
	
	@Test
	public void testActionPerformedO() {
		Controller c = new Controller();
		c.startOsprey();
	}
	
	@Test
	public void testActionPerformedH() {
		Controller c = new Controller();
		c.startHarrier();
	}
	
	@Test
	public void testSave() {
		Controller c = new Controller();
		c.save();
	}


}
