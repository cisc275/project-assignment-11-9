package Project;
import static org.junit.Assert.*;

import org.junit.Test;

public class ProjectTest {

	@Test
	public void animalTestInteract() {
		Animal a = new Fish(0, 0, 1);
		GameObject g = new Fish(0, 0, 1);
		double xVel = a.getXVel();
		double yVel = a.getYVel();
		a.interact(g);
		
		assertEquals(a.getXVel(), -1 * xVel, .00001);
		assertEquals(a.getYVel(), -1 * yVel, .00001);
	}
	
	@Test
	public void animalTestMove() {
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
	public void animalTestUpdateDirection() {
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
	public void animalTestIncrementAnimation() {
		Animal a = new Fish(0, 0, 1);
		a.setAnimationDelay(4);
		a.setAnimationCounter(3);
		a.incrementAnimation();
		assertEquals(a.getAnimationCounter(), 3);
		assertEquals(a.getAnimationDelay(), 5);
		
		a.incrementAnimation();
		assertEquals(a.getAnimationDelay(), 0);
		assertEquals(a.getAnimationCounter(), 0);
	}
	
	@Test
	public void animalTestMoveBounded() {
		Animal a = new Harrier();
		a.setXPos(200);
		a.setXVel(15);
		a.moveBounded(1000, 1000, 0);
		assertEquals(a.getXPos(), 215, 0.001);
		a.setXPos(-200);
		a.setXVel(-15);
		a.moveBounded(1000, 1000, 0);
		assertEquals(a.getXPos(), -215, 0.0001);
		
		a.setXVel(15);
		a.setXPos(1620);
		a.moveBounded(1620, TitleView.FRAME_HEIGHT, 0);
		assertEquals(a.getXVel(), 0, 0.001);
		

		a.setXPos(0);
		a.setYPos(920);
		a.setYVel(15);
		a.moveBounded(TitleView.FRAME_WIDTH, 920, 0);
		assertEquals(a.getYVel(), 0, 0.0001);
		
	}
	
	@Test
	public void animalTestTwitch() {
		Animal a = new Mouse(0,0);
		a.setChanceMod(1);
		a.twitch();
		assertEquals(a.calcSpeed() > 0, true);
		
		
	}
	
	@Test
	public void animalTestTurnCCW() {
		Animal a = new Harrier();
		a.setXVel(10);
		a.setYVel(10);
		a.updateDirection();
		a.turnCCW(Math.PI/2);
		assertEquals((int)(a.getAngle() * 180/Math.PI), 45);
	}
	
	@Test
	public void animalTestCalcSpeed() {
		Animal a = new Harrier();
		a.calcSpeed();
		a.setAngle(0);
		assertEquals((int)a.calcSpeed(), 0);
		a.setXVel(3);
		a.setYVel(4);
		assertEquals((int)a.calcSpeed(), 5);
		assertEquals(a.getAngle(), 0, 0.001);
		assertEquals(a.getChanceMod(), 0, 0.0001);
	}
	
	@Test
	public void animalGetSpeedMod() {
		Animal a = new Harrier();
		a.setSpeedMod(0);
		assertEquals(a.getSpeedMod(), 0, 0.0001);
	}
	
	@Test
	public void animalSetDirection() {
		Animal a = new Harrier();
		a.setDirection(Direction.EAST);
		assertEquals(a.getDirection(), Direction.EAST);
	}
	
	@Test
	public void fishTestInteract() {
		Osprey o = new Osprey();
		Fish f = new Fish(0, 0, 1);
		f.setSize(2);
		o.setXVel(80);
		f.interact(o);
		assertEquals(o.getXVel(), 40, 0.0001);
		
		o.setXVel(18);
		f.interact(o);
		assertEquals((int)o.getXVel(), 19);
	}
	
	@Test
	public void fishTestGetSize() {
		Fish f = new Fish(0, 0, 1);
		assertEquals(f.getSize(), 1);
		f.setSize(2);
		assertEquals(f.getSize(), 2);
	}
	
	@Test
	public void foxTestInteract() {
		Fox f = new Fox(0, 0);
		Harrier h = new Harrier();
		int score = h.getScore();
		double vision = h.getVision();
		f.interact(h);
		
		assertEquals(h.getScore(), score - 30);
		assertEquals((int)h.getVision(), (int)vision - 75);
	}

	@Test
	public void foxTestChase() {
		Fox f = new Fox(0, 0);
		Harrier h = new Harrier();
		
		h.setXPos(100);
		h.setYPos(-100);
		f.chase(h);
		assertEquals(f.getXVel(), 2.474874, 0.0001);
		assertEquals(f.getYVel(), -2.474874, 0.0001);
		
		h.setXPos(-100);
		h.setYPos(-100);
		f.chase(h);
		assertEquals(f.getXVel(), -2.474874, 0.0001);
		assertEquals(f.getYVel(), -2.474874, 0.0001);
	}
	
	@Test
	public void foxTestRoam() {
		Fox f = new Fox(150,150);
		Harrier h = new Harrier();
		h.setXPos(200);
		h.setYPos(200);
		f.setChanceMod(1);
		f.roam(h, TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, -1);
		assertEquals(f.getXVel(), 2.474874, 0.0001);
		assertEquals(f.getYVel(), 2.474874, 0.0001);
		
		f.setXPos(-300);
		f.setYPos(-300);
		f.roam(h, TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, -1);
		assertEquals(f.getXVel() != 2.474874, true);
		assertEquals(f.getYVel() != 2.474874, true);
		assertEquals(f.calcSpeed() > 0, true);
		
		h.setXPos(0);
		h.setYPos(0);
		f.roam(h, TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, -1);
		assertEquals(f.getXVel() != 2.474874, true);
		assertEquals(f.getYVel() != 2.474874, true);
		assertEquals(f.calcSpeed() > 0, true);
	}

	@Test
	public void gameObjectTestCalcDist() {
		GameObject o = new Osprey();
		o.calcDist(o);
		assertEquals((int)o.calcDist(o), 0);
		
		o.calcDist();
		assertEquals((int)o.calcDist(), 0);
	}

	@Test
	public void gameObjectTestApproximateDirection() {
		GameObject o = new Harrier();
		Mouse m = new Mouse(0, 0);
		o.getApproximateDirection(m);
		assertEquals(o.getApproximateDirection(m),Direction.SOUTH);
		
		o.setXPos(50);
		m.setXPos(75);
		o.setYPos(50);
		m.setYPos(60);
		o.getApproximateDirection(m);
		assertEquals(o.getApproximateDirection(m), Direction.WEST);
		
		o.setXPos(75);
		m.setXPos(50);
		o.setYPos(60);
		m.setYPos(50);
		o.getApproximateDirection(m);
		assertEquals(o.getApproximateDirection(m), Direction.EAST);
		
		o.setXPos(0);
		m.setXPos(0);
		o.setYPos(50);
		m.setYPos(60);
		o.getApproximateDirection(m);
		assertEquals(o.getApproximateDirection(m), Direction.NORTH);
		
	}
	
	@Test
	public void gameObjectTestGetX() {
		GameObject o = new Osprey();
		o.setXPos(0);
		assertEquals((int)o.getXPos(), 0);
	}
	
	@Test
	public void gameObjectTestGetY() {
		GameObject o = new Osprey();
		o.setYPos(0);
		assertEquals((int)o.getYPos(), 0);
	}
	
	@Test
	public void gameObjectTestGetXWidth() {
		GameObject o = new Osprey();
		o.setXWidth(0);
		assertEquals((int)o.getXWidth(), 0);
	}
	
	@Test
	public void gameObjectTestGetYWidth() {
		GameObject o = new Osprey();
		o.setYWidth(0);
		assertEquals((int)o.getYWidth(), 0);
	}
	
	@Test
	public void goldenFishTestInteract() {
		Osprey o = new Osprey();
		GoldenFish gf = new GoldenFish(0, 0);
		assertEquals(gf.getTutorial(), false);
		o.setXVel(5);
		assertEquals(gf.getCorrect(), false);
		gf.interact(o);
		assertEquals(gf.getCorrect(), true);
		assertEquals(o.getXVel(), 10, 0.0);
		
		o.setXVel(75);
		gf.interact(o);
		assertEquals(o.getXVel(), 40, 0.0);
		
	}
	
	@Test
	public void testGoldenFishTutorial() {
		GoldenFish gf = new GoldenFish(0, 0, true);
		assertEquals(gf.getTutorial(), true);
	}
	
	@Test
	public void testInteract() {
		GoldenMouse gm = new GoldenMouse(0, 0);
		Harrier h= new Harrier();
		assertEquals(gm.getTutorial(), false);
		h.setScore(200);
		assertEquals(gm.getCorrect(), false);
		gm.interact(h);
		assertEquals(gm.getCorrect(), true);
		assertEquals(h.getScore(), 300);
		
		h.setVision(600);
		gm.interact(h);
		assertEquals(h.getVision(), 600, 0);
	}
	
	@Test
	public void testGoldenMouseTutorial() {
		GoldenMouse gm = new GoldenMouse(0, 0, true);
		assertEquals(gm.getTutorial(), true);
	}
	
	@Test
	public void harrierTestMoveNorth() {
		
		Harrier h = new Harrier();
		h.setYVel(1);
		h.goNorth();
		assertEquals((int)h.getYVel(), 0);
		
		h.setXVel(1);
		h.goNorth();
		assertEquals(h.getXVel() < 1, true);
		h.setXVel(20);
		h.setYVel(20);
		h.goNorth();
		assertEquals(h.getXVel() < 1, true);
	}
	
	@Test
	public void harrierTestMoveSouth() {
		
		Harrier h = new Harrier();
		h.setYVel(-1);
		h.goSouth();
		assertEquals((int)h.getYVel(), 0);
		
		h.setXVel(1);
		h.goSouth();
		assertEquals(h.getXVel() < 1, true);
	}
	
	@Test
	public void harrierTestMoveEast() {
		
		Harrier h = new Harrier();
		h.setXVel(-1);
		h.goEast();
		assertEquals((int)h.getXVel(), 0);
		
		h.setYVel(1);
		h.goEast();
		assertEquals(h.getYVel() < 1, true);
	}
	
	@Test
	public void harrierTestMoveWest() {
		
		
		Harrier h = new Harrier();
		h.setXVel(1);
		h.goWest();
		assertEquals((int)h.getXVel(), 0);
		
		h.setYVel(1);
		h.goWest();
		assertEquals(h.getYVel() < 1, true);
	}
	
	@Test
	public void harrierTestScore() {
		Harrier h = new Harrier();
		h.setScore(1);
		assertEquals(h.getScore(), 1);
	
		
	}
	@Test
	public void harrierTestDie() {
		Harrier h = new Harrier();
		h.die();
		assertEquals((int)h.getXPos(),0);
		assertEquals((int)h.getYPos(),0);
	}
	
	@Test
	public void harrierTestVision() {
		Harrier h = new Harrier();
		h.setVision(10);
		assertEquals(10, h.getVision(), 0.01);
	}
	
	@Test
	public void modelTestCollisions() {
		Model m = new HarrierModel();
			
			Harrier h = new Harrier();
			Mouse mo = new Mouse(2,2);
		
			assertEquals(m.isCollision(h, mo), true);
	}
	
	@Test
	public void modelTimeTest() {
		Model m = new HarrierModel();
		
		m.setTime(1);
		assertEquals(m.getTime(), 1);
	}
	
	@Test
	public void mouseTestInteractHarrier() {
		Mouse m = new Mouse(0, 0);
		Harrier h = new Harrier();
		
		m.interact(h);
		
		assertEquals(h.getScore(), 10);
		assertEquals(325, h.getVision(), 0.01);
		
		h.setVision(75);
		m.interact(h);
		
		assertEquals((int)h.getVision(),100);
		
		h.setVision(700);
		m.interact(h);
		assertEquals((int)h.getVision(), (int)Harrier.MAX_VISION);
	}
	
	@Test
	public void mouseRoamTest() {
		Mouse m = new Mouse(0, 0);
		m.setChanceMod(1);
		m.roam(20, 20, 1);
		assertEquals(m.calcSpeed() > 0, true);
	}
	
	@Test
	public void harrierModelTestIsEnd() {
		HarrierModel hm = new HarrierModel();
		hm.getHarrier().setVision(30);
		assertEquals(hm.isEnd(), false);
		hm.getHarrier().setVision(0);
		assertEquals(hm.isEnd(), true);
	}

	@Test
	public void harrierModelTestIsWin() {
		HarrierModel hm = new HarrierModel();
		hm.setTime(100000000);
		assertEquals(hm.isWin(), true);
	
	}
	@Test
	public void harrierModelTestUpdate() {
		HarrierModel hm = new HarrierModel();
		Mouse m = new Mouse(0, 0);
		Tree t = new Tree(10, 10);
		int origMouseArray=hm.getMice().size();
		int origTreeArray=hm.getTrees().size();
		
		
		hm.getMice().add(m);
		hm.getTrees().add(t);
		
		
		assertEquals(origMouseArray+1, hm.getMice().size());
		assertEquals(origTreeArray+1, hm.getTrees().size());

		hm.getMice().remove(0);
		hm.getTrees().remove(0);
		hm.getHarrier().setYVel(-1);
		hm.update();
		assertEquals(hm.getStage(), HarrierModel.Tutorial.TWO);
		hm.getHarrier().setXVel(1);
		hm.update();
		assertEquals(hm.getStage(), HarrierModel.Tutorial.THREE);
		hm.getHarrier().setYVel(1);
		hm.update();
		assertEquals(hm.getStage(), HarrierModel.Tutorial.FOUR);
		hm.getHarrier().setXVel(-1);
		hm.update();
		assertEquals(hm.getStage(), HarrierModel.Tutorial.FIVE);
		
		hm.getTwigs().remove(0);
		hm.update();
		assertEquals(hm.getStage(), HarrierModel.Tutorial.SIX);
		
		hm.getMice().remove(0);
		hm.update();
		assertEquals(hm.getStage(), HarrierModel.Tutorial.SEVEN);
		
		hm.getHarrier().setVision(Harrier.INITIAL_VISION);
		hm.update();
		assertEquals(hm.getStage(), HarrierModel.Tutorial.EIGHT);
		
		hm.getMice().remove(0);
		hm.update();
		assertEquals(hm.getStage(), HarrierModel.Tutorial.NINE);
		
		hm.getHarrier().setXPos(0);
		hm.update();
		assertEquals(hm.getStage(), HarrierModel.Tutorial.NONE);
		
		hm.update();
		assertEquals(hm.getFoxes().size(), 3);
		assertEquals(hm.getMice().size(), 20);
		assertEquals(hm.getTrees().size(), 125);
		assertEquals(hm.getTwigs().size(), 20);
		assertEquals(hm.getTime(), 1);
		
		Twig tw = new Twig(0,0);
		hm.getHarrier().setXPos(0);
		hm.getHarrier().setYPos(0);
		hm.getTwigs().add(tw);
		hm.update();
		assertEquals(hm.getTwigs().size(), 20);
	}
	
	@Test
	public void ospreyModelTestIsEnd() {
		OspreyModel om= new OspreyModel();
		
		om.getOsprey().setXVel(0);
		assertEquals(om.isEnd(), true);
	}

	@Test
	public void ospreyModelTestIsWin() {
		OspreyModel om = new OspreyModel();
		
		om.getOsprey().setXPos(55000);
		
		assertEquals(om.isWin(), true);
	}

	
	@Test
	public void ospreyModelApplyResistanceCheck() {
		OspreyModel om = new OspreyModel();
		
		om.getOsprey().setYPos(Osprey.MAX_HEIGHT);
	}
	
	@Test
	public void ospreyModelTestUpdate() {
		
		OspreyModel om = new OspreyModel();
		
		Fish f = new Fish(3,0, 0);
		Seaweed s = new Seaweed(10, 10);
		int origFishArray=om.getFish().size();
		int origSeaweedArray=om.getSeaweed().size();
		f.setXVel(1);
		
		
		om.getFish().add(f);
		om.getSeaweed().add(s);
		
		assertEquals(origFishArray+1, om.getFish().size());
		assertEquals(origSeaweedArray+1, om.getSeaweed().size());
		
		om.update();
		assertEquals((int)f.getXPos(), 4);
		assertEquals((int)om.getOsprey().getXPos(), 5);
		
		om.getOsprey().setYPos(TitleView.FRAME_HEIGHT);
		om.update();
		assertEquals(om.getTutorial(), OspreyModel.Tutorial.TWO);
		
		om.getFish().remove(0);
		om.update();
		assertEquals(om.getFish().size(), 1);
		om.getOsprey().setXVel(100);
		om.update();
		assertEquals(om.getTutorial(), OspreyModel.Tutorial.THREE);
		
		om.getSeaweed().remove(0);
		om.getSeaweed().remove(0);
		om.getSeaweed().remove(0);
		om.getSeaweed().remove(0);
		om.getSeaweed().remove(0);
		om.getSeaweed().remove(0);
		om.getFish().remove(0);
		om.update();
		assertEquals(om.getSeaweed().size(), 13);
		assertEquals(om.getFish().size(), 1);
		
		om.getOsprey().setXVel(Osprey.START_SPEED);
		om.update();
		assertEquals(om.getTutorial(), OspreyModel.Tutorial.FOUR);
	}
	
	@Test
	public void ospreyModelTestGenerate() {
		OspreyModel om = new OspreyModel();
		om.generate();
		assertEquals(om.getFish().size(), 5);
		assertEquals(om.getSeaweed().size(), 8);
	}
	
	@Test
	public void ospreyModelTestResistance() {
		OspreyModel om = new OspreyModel();
		om.generate();
		assertEquals(om.getFish().size(), 5);
		assertEquals(om.getSeaweed().size(), 8);
	}
	
	@Test
	public void ospreyTestDive() {
		Osprey o = new Osprey();
		
		o.dive();
		
		assertEquals((int)o.getYVel(), 15);
	}

	@Test
	public void ospreyTestRise() {
		
		Osprey o = new Osprey();
		
		o.rise();
		
		assertEquals((int)o.getYVel(), -15);
		
	}
	@Test
	public void ospreyTestmove() {
		Osprey o=new Osprey();
		o.setYVel(2);
		o.move();
		assertEquals((int)o.getXPos(),5);
		assertEquals((int)o.getYPos(),2);
	}
	
	@Test
	public void seaweedInteracttest() {
		
		Osprey o= new Osprey();
		
		Seaweed s = new Seaweed(0,0);
		
		s.interact(o);
		
		
		assertEquals((int) s.getXPos(), 0);
		
		assertEquals((int) s.getYPos(), 0);
		
		s.setXPos(5);
		s.setYPos(5);
		
		assertEquals((int) s.getXPos(), 5);
		
		assertEquals((int) s.getYPos(), 5);
		
		o.setXVel(20);
		o.setIsRecovering(false);
		s = new Seaweed(0,0);
		s.interact(o);
		assertEquals((int)o.getXVel(), 19);
	}
	
	@Test
	public void treeInteracttest() {
		
		Harrier h= new Harrier();
		
		Tree t = new Tree(0,0);
		
		assertEquals((int)h.getVision(), 300);
		t.interact(h);
		
		assertEquals((int)h.getVision(), 275);
		assertEquals(h.getScore(), -10);
		assertEquals((int) h.getXPos(), 0);
		
		assertEquals((int) h.getYPos(), 0);
		
		t.setXPos(5);
		t.setYPos(5);
		
		assertEquals((int) t.getXPos(), 5);
		
		assertEquals((int) t.getYPos(), 5);
		
		h.setVision(135);
		t.interact(h);
		
		assertEquals(h.getScore(), -10);
			
		assertEquals((int)h.getVision(),135);
		
		t = new Tree(0,0);
		h.setVision(25);
		t.interact(h);
		assertEquals((int)h.getVision(), (int)Harrier.MIN_VISION);
		
	}
	
	@Test
	public void twigestInteractHarrier() {
		Twig t = new Twig(0, 0);
		Harrier h = new Harrier();
		
		t.interact(h);
		
		assertEquals(h.getScore(), 20);
		
		assertEquals((int) t.getXPos(), 0);
		
		assertEquals((int) t.getYPos(), 0);
		
		t.setXPos(5);
		t.setYPos(5);
		
		assertEquals((int) t.getXPos(), 5);
		
		assertEquals((int) t.getYPos(), 5);
		
		t.interact(h);
		
		assertEquals(h.getScore(), 40);
		
		
		h.setVision(75);
		t.interact(h);
		
		h.setVision(700);
		t.interact(h);
		assertEquals((int)h.getVision(), (int)Harrier.MAX_VISION);
	}
}