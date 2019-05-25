package Project;
import static org.junit.Assert.*;

import org.junit.Test;

public class AnimalTest {

	@Test
	public void testInteract() {
		Animal a = new Fish(0, 0, 1);
		GameObject g = new Fish(0, 0, 1);
		double xVel = a.getXVel();
		double yVel = a.getYVel();
		a.interact(g);
		
		assertEquals(a.getXVel(), -1 * xVel, .00001);
		assertEquals(a.getYVel(), -1 * yVel, .00001);
	}
	
	@Test
	public void testMove() {
		Animal a = new Fish(0, 0, 1);
		double xPos = a.getXPos();
		double yPos = a.getYPos();
		double xVel = a.getXVel();
		double yVel = a.getYVel();
		a.move();
		
		assertEquals(a.getXPos(), xPos + xVel, .00001);
		assertEquals(a.getYPos(), yPos + yVel, .00001);
	}
	
	@Test
	public void testUpdateDirection() {
		Animal a = new Harrier();
		
		a.setXVel(1);
		a.setYVel(1);
		a.updateDirection();
		assertEquals(a.getDirection(), Direction.SOUTHEAST);
		
		a.setYVel(0);
		a.updateDirection();
		assertEquals(a.getDirection(), Direction.EAST);

		
		a.setYVel(-1);
		a.updateDirection();
		assertEquals(a.getDirection(), Direction.NORTHEAST);
		
		a.setXVel(-1);
		a.setYVel(1);
		a.updateDirection();
		assertEquals(a.getDirection(), Direction.SOUTHWEST);
		
		a.setYVel(0);
		a.updateDirection();
		assertEquals(a.getDirection(), Direction.WEST);
		
		a.setYVel(-1);
		a.updateDirection();
		assertEquals(a.getDirection(), Direction.NORTHWEST);
		
		a.setXVel(0);
		a.setYVel(1);
		a.updateDirection();
		assertEquals(a.getDirection(), Direction.SOUTH);
		
		a.setYVel(-1);
		a.updateDirection();
		assertEquals(a.getDirection(), Direction.NORTH);
	}
	
	@Test
	public void testIncrementAnimation() {
		Animal a = new Fish(0, 0, 1);
		a.setAnimation(5);
		a.setAnimation(3);
		a.incrementAnimation();
		assertEquals(a.getAnimation(), 0);
		
		a.setAnimation(7);
		a.incrementAnimation();
		assertEquals(a.getAnimation(), 2);
	}
	
	@Test
	public void testMoveBounded() {
		Animal a = new Harrier();
		a.setXPos(200);
		a.setXVel(15);
		a.moveBounded(TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, 0);
		assertEquals(a.getXPos(), 215, 0.001);
		a.setXPos(-200);
		a.setXVel(-15);
		a.moveBounded(TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, 0);
		assertEquals(a.getXPos(), -215, 0.0001);
		
		
		a.setXPos(1620);
		a.moveBounded(TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, 0);
		assertEquals(a.getXVel(), 0, 0.001);
		a.moveBounded(TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, 1);
		

		a.setXPos(0);
		a.setYPos(920);
		a.moveBounded(TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, 0);
		assertEquals(a.getYVel(), 0, 0.0001);
		
		a.moveBounded(TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, 1);
	}
	
	@Test
	public void testTwitch() {
		Animal a = new Mouse(0,0);
		a.twitch();
		a.setChanceMod(1);
		a.twitch();
		
		
	}
	
	@Test
	public void testTurnCCW() {
		Animal a = new Harrier();
		a.turnCCW(0.261);
	}
	
	@Test
	public void testCalcSpeed() {
		Animal a = new Harrier();
		a.calcSpeed();
		a.setAngle(0);
		assertEquals(a.getAngle(), 0, 0.001);
		assertEquals(a.getChanceMod(), 0, 0.0001);
	}

	
	@Test
	public void getSpeedMod() {
		Animal a = new Harrier();
		a.setSpeedMod(0);
		assertEquals(a.getSpeedMod(), 0, 0.0001);
	}
	
	@Test
	public void setDirection() {
		Animal a = new Harrier();
		a.setDirection(Direction.EAST);
		assertEquals(a.getDirection(), Direction.EAST);
	}
}