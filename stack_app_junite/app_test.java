package stack_app_junite;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import stack_app_classes.code;

class app_test {
	code test1;
	

	@Test
	void test_convert() {
		test1=new code();
		Assertions.assertThrows(RuntimeException.class, ()->test1.check_expresion("5+7*"));
		Assertions.assertThrows(RuntimeException.class, ()->test1.check_expresion("5+(7"));
		Assertions.assertThrows(RuntimeException.class, ()->test1.check_expresion("5++7"));
		Assertions.assertThrows(RuntimeException.class, ()->test1.check_expresion("5-/7"));
		Assertions.assertThrows(RuntimeException.class, ()->test1.check_expresion("5/*7"));
		assertEquals("a1 bz c * a / +",test1.infixToPostfix("a1+bz*c/a"));
		assertEquals("0 51 - 6 +",test1.infixToPostfix("-51+(6)"));
		assertEquals("0 51 - 6 +",test1.infixToPostfix("(-51)+(6)"));
		assertEquals("5 6 5 * +",test1.infixToPostfix("5+(6*5)"));
		assertEquals("0 5 - 0 6 - *",test1.infixToPostfix("-5 * -6"));
		assertEquals("5 0 63 - 0 744 - * 0 8 - 0 7 - * + *",test1.infixToPostfix("5*(-63*-744+-8*-7)"));
		assertEquals("542 6 +",test1.infixToPostfix("+542+(6)"));
		assertEquals("0 12 - 2 + 0 7 - *",test1.infixToPostfix("(-12 + 2) * -7"));
		assertEquals("0 7 - 0 2 - / 7 2 / +",test1.infixToPostfix("(-7/-2)+(7/2)"));
		assertEquals("a b * c /",test1.infixToPostfix("a * b / c"));
		assertEquals("a b 0 c - - d + / e a - * c *",test1.infixToPostfix("(a/  (b - -c + d)) *  (e - a)  *  c"));//space
		assertEquals("0 a - 0 b - / 0 c - - d e * + a c * -",test1.infixToPostfix("-a / -b - -c + d * e - a * c"));
		assertEquals("a 0 b - * 5 +",test1.infixToPostfix("a * -b + 5"));
		assertEquals("0 1 - 0 2 - + 4 * 3 +",test1.infixToPostfix("(-1+-2)*4+3"));
		assertEquals("10 3 5 * 16 4 - / +",test1.infixToPostfix("10+3*5/(16-4)"));
		assertEquals("2 0 2.14 - 3.14 + 0 5.8 - 8 + * +",test1.infixToPostfix("2+(-2.14+3.14)*(-5.8+8)"));
		assertEquals("0 0 0 0 966 - - - - ",test1.infixToPostfix("-(-(-(-966)))"));
		assertEquals("0 0 0 0 966 - 5 + - - - ",test1.infixToPostfix("-(-(-(-966+5)))"));
		assertEquals("0 0 0 966 - - - ",test1.infixToPostfix("-(-(-966))"));
		assertEquals("0 0 22 - 2 / - ",test1.infixToPostfix("-(-22/2)"));
		assertEquals("0 0 22 - 2 / - ",test1.infixToPostfix("(-(-22/2))"));
		assertEquals("0 3 * 2 /",test1.infixToPostfix("0*3/2"));
		assertEquals("6 54 + 2 *",test1.infixToPostfix("(6+54)  *2"));
		assertEquals("0 A - ",test1.infixToPostfix("-A"));
		assertEquals("040 2 + 2 -",test1.infixToPostfix("    040+2-2      "));
		assertEquals("3 4 44 43 - + *",test1.infixToPostfix(" 3 * (4+(44-(43)))"));
		assertEquals("3 4 44 0 43 - + + *",test1.infixToPostfix(" 3 * (4+(44+-(43)))"));
		assertEquals("88 0 99 - / 88 *",test1.infixToPostfix("88/-99*88"));
	}
	@Test
	void test_evalute() {
		test1=new code();
		Assertions.assertThrows(RuntimeException.class, ()->test1.evaluate("51 s +"));
		assertEquals(57,test1.evaluate("51 06 +"));
		assertEquals(35,test1.evaluate("5 6 05 * +"));
		assertEquals(-45,test1.evaluate("0 51 - 6 +"));//these is -51+6=-45
		assertEquals(-45,test1.evaluate("-51 6 +"));//same the previous example -51+6=-45
		assertEquals(25,test1.evaluate("0 5 - 06 5 * +"));// -5+6*5
		assertEquals(98,test1.evaluate("12 2 + 7 *"));
		assertEquals(0,test1.evaluate("-2 2 + 7 *"));
		assertEquals(7,test1.evaluate("7 2 / 7 2 / +"));
		assertEquals(7,test1.evaluate("0 7 - 0 2 - / 7 2 / +"));
		assertEquals(1,test1.evaluate("3 2 /"));
		assertEquals(8,test1.evaluate("7 0 * 8 +"));	
		assertEquals(9,test1.evaluate("9 0 - "));
		assertEquals(0,test1.evaluate("0 "));
		assertEquals(6,test1.evaluate(" 6"));
		assertEquals(50,test1.evaluate(" 100 50 -"));
		assertEquals(8,test1.evaluate("20 10 5 - 5 + / 4 2 - * 2 *"));
		assertEquals(400,test1.evaluate("900 2 / 50 -"));
	}
}
