package com.expression.parser;

import org.junit.Test;

public class SpeedTest {

	@Test
	public void testOne() {

		final long time1 = System.currentTimeMillis();
		Parser.simpleEval(
				"6.5*7.8^2.3 + (3.5^3+7/2)^3 -(5*4/(2-3))*4 + 6.5*7.8^2.3 + (3.5^3+7/2)^3 -(5*4/(2-3))*4 + 6.5*7.8^2.3 + (3.5^3+7/2)^3 -(5*4/(2-3))*4 + 6.5*7.8^2.3 + (3.5^3+7/2)^3 -(5*4/(2-3))*4");
		final long time2 = System.currentTimeMillis();
		testTwoHelper("time test one:", time2, time1);

	}

	@Test
	public void testTwo() {

		final long time1 = System.currentTimeMillis();

		for (int i = 0; i < 1000000; i++) {
			Parser.simpleEval(
					"6.5*7.8^2.3 + (3.5^3+7/2)^3 -(5*4/(2-3))*4 + 6.5*7.8^2.3 + (3.5^3+7/2)^3 -(5*4/(2-3))*4 + 6.5*7.8^2.3 + (3.5^3+7/2)^3 -(5*4/(2-3))*4 + 6.5*7.8^2.3 + (3.5^3+7/2)^3 -(5*4/(2-3))*4");
		}

		final long time2 = System.currentTimeMillis();
		testTwoHelper("time test two:", time2, time1);

	}

	private void testTwoHelper(String msg, long time2, long time1) {
		System.out.println(msg + (time2 - time1));
	}

	@Test
	public void testThree() {

		final long time1 = System.currentTimeMillis();
		Parser.simpleEval("6.5*7.8^2.3 + (3.5^3+7/2)^3 -(5*4/(2-3))*4");
		final long time2 = System.currentTimeMillis();
		testTwoHelper("time test three:", time2, time1);

	}
}
