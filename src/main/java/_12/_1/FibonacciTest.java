package _12._1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


class FibonacciTest {

	private static final int[] NUMBERS = new int[] { 3, 5, 8, 12, 9, 18, 15, 10, 7, 11, 20 };
	
	@Test
	void test() {

		int count = 0;
		long start;
		long stop;
		long time;
		start = System.nanoTime();
		FibonacciRecursive r = new FibonacciRecursive();
		long[] rResult = new long[NUMBERS.length];
		for (int i : NUMBERS) {
			rResult[count] = r.calculate(i);
			count++;
		}
		stop = System.nanoTime();
		time = (stop - start) / 1000000L;
		System.out.println("Rekursiv runtime: " + time + " ms");



		count = 0;
		start = System.nanoTime();
		FibonacciParallel p = new FibonacciParallel();
		long[] pResult = new long[NUMBERS.length];
		for (int i : NUMBERS) {
			pResult[count] = p.calculate(i);
			count++;
		}
		stop = System.nanoTime();
		time = (stop - start) / 1000000L;
		System.out.println("Parallel runtime: " + time + " ms");


		count = 0;
		start = System.nanoTime();
		FibonacciDynamic d = new FibonacciDynamic();
		long[] dResult = new long[NUMBERS.length];
		for (int i : NUMBERS) {
			dResult[count] = d.calculate(i);
			count++;
		}
		stop = System.nanoTime();
		time = (stop - start) / 1000000L;
		System.out.println("Dynamic runtime: " + time + " ms");


		count = 0;
		start = System.nanoTime();
		FibonacciDynamicParallel dp = new FibonacciDynamicParallel();
		long[] dpResult = new long[NUMBERS.length];
		for (int i : NUMBERS) {
			dpResult[count] = dp.calculate(i);
			count++;
		}
		stop = System.nanoTime();
		time = (stop - start) / 1000000L;
		System.out.println("Dynamic Parallel runtime: " + time + " ms");



		for (int i = 0; i < NUMBERS.length; i++) {
			assertEquals(rResult[i], pResult[i]);
			assertEquals(rResult[i], dResult[i]);
			assertEquals(rResult[i], dpResult[i]);
			assertEquals(pResult[i], dResult[i]);
			assertEquals(pResult[i], dpResult[i]);
			assertEquals(dResult[i], dpResult[i]);
		}

	}
}
