package _12._1.lukas;

import provided._12._1.Fibonacci;

public class FibonacciTiming {
	private static final int[] NUMBERS = new int[]{ 3, 5, 8, 12, 9, 18, 15, 10, 7, 11, 20 };

	private static final Fibonacci RECURSIVE = new FibonacciRecursive();
	private static final Fibonacci PARALLEL_RECURSIVE = new FibonacciParallelRecursive();
	private static final Fibonacci DYNAMIC = new FibonacciDynamic();
	private static final Fibonacci PARALLEL_DYNAMIC = new FibonacciParallelDynamic();

	public static void main(String[] args) {
		System.out.println("FibonacciRecursive (first): " + formatTime(
				Timing.measureNanos(() -> runOnNumbers(RECURSIVE))
		));
		System.out.println("FibonacciParallelRecursive (first): " + formatTime(
				Timing.measureNanos(() -> runOnNumbers(PARALLEL_RECURSIVE))
		));
		System.out.println("FibonacciDynamic (first): " + formatTime(
				Timing.measureNanos(() -> runOnNumbers(DYNAMIC))
		));
		System.out.println("FibonacciParallelDynamic (first): " + formatTime(
				Timing.measureNanos(() -> runOnNumbers(PARALLEL_DYNAMIC))
		));

		System.out.println("FibonacciRecursive (1M iteration average): " + formatTime(
				Timing.measureAverageNanos(1000000, () -> runOnNumbers(RECURSIVE))
		));
		System.out.println("FibonacciParallelRecursive (4 iteration average): " + formatTime(
				Timing.measureAverageNanos(4, () -> runOnNumbers(PARALLEL_RECURSIVE))
		));
		System.out.println("FibonacciDynamic (1M iteration average): " + formatTime(
				Timing.measureAverageNanos(1000000, () -> runOnNumbers(DYNAMIC))
		));
		System.out.println("FibonacciParallelDynamic (1K iteration average): " + formatTime(
				Timing.measureAverageNanos(1000, () -> runOnNumbers(PARALLEL_DYNAMIC))
		));
	}

	private static void runOnNumbers(Fibonacci calculator) {
		for(int n : NUMBERS) {
			calculator.calculate(n);
		}
	}

	private static String formatTime(double nanos) {
		double digits = Math.log10(nanos);
		if(digits > 9) {
			return Math.round(nanos / 1e7) / 1e2 + " seconds";
		} else if(digits > 6) {
			return Math.round(nanos / 1e4) / 1e2 + " milliseconds";
		} else if(digits > 3) {
			return Math.round(nanos / 1e1) / 1e2 + " microseconds";
		} else {
			return Math.round(nanos) + " nanosecond";
		}
	}
}
