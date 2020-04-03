package stack_junite;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import stake_classes.stack;

class sstack_test {

	stack test=new stack();
	
	@Test
	void test() {
		test=new stack();
		Assertions.assertThrows(RuntimeException.class, ()->test.peek());
		Assertions.assertThrows(RuntimeException.class, ()->test.pop());
		assertEquals(0,test.size());
		test.push(3);
		test.push("ahmed");
		test.push(5);
		test.push(8);
		test.push("hi");
		assertEquals("hi",test.pop());
		assertEquals(8,test.peek());
		assertEquals(4,test.size());
		assertEquals(8,test.pop());
		assertEquals(5,test.peek());
		assertEquals(3,test.size());
		assertEquals(5,test.pop());
		assertEquals("ahmed",test.peek());
		assertEquals(2,test.size());
		assertEquals("ahmed",test.pop());
		assertEquals(3,test.peek());
		assertEquals(1,test.size());
		assertEquals(3,test.pop());
		Assertions.assertThrows(RuntimeException.class, ()->test.peek());
		Assertions.assertThrows(RuntimeException.class, ()->test.pop());
		assertEquals(0,test.size());
	}
	
}
