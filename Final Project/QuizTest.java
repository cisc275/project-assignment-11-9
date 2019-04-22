package Project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QuizTest {

	
	String[] questions= {"test", "test2"};
	char[] answers= {'a','b'};
	Quiz q= new Quiz(questions, answers);
	
	
	@Test
	void testIsRight() {
		assertTrue(q.isRight(1, 'a'));
		
		
		fail("Not yet implemented");
	}

	@Test
	void testGetQuestions() {
		
		assertArrayEquals(new String[] {"test", "test2"}, q.getQuestions()); 
		fail("Not yet implemented");
	}

	@Test
	void testGetAnswers() {
		assertArrayEquals(new char[] {'a', 'b'}, q.getAnswers());
		fail("Not yet implemented");
	}

}
