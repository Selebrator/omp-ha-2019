package _12._1.lukas;

import provided._12._1.Fibonacci;

public class FibonacciTiming {
	private static final int[] NUMBERS = new int[]{ 3, 5, 8, 12, 9, 18, 15, 10, 7, 11, 20 };

	private static final Fibonacci RECURSIVE = new FibonacciRecursive();
	private static final Fibonacci PARALLEL_RECURSIVE = new FibonacciRecursiveParallel();
	private static final Fibonacci DYNAMIC = new FibonacciDynamicRecursivePersistent();
	private static final Fibonacci PARALLEL_DYNAMIC = new FibonacciDynamicRecursivePersistentParallel();
	private static final Fibonacci ITERATIVE = new FibonacciDynamicIterative();

	// can take a few minutes
	public static void main(String[] args) {
		System.out.println("# Sequential");
		System.out.println("FibonacciRecursive (first): " + formatTime(
				Timing.measureNanos(() -> generateHeat(RECURSIVE))
		));
		System.out.println("FibonacciDynamicRecursivePersistent (first): " + formatTime(
				Timing.measureNanos(() -> generateHeat(DYNAMIC))
		));
		System.out.println("FibonacciDynamicIterative (first): " + formatTime(
				Timing.measureNanos(() -> generateHeat(ITERATIVE))
		));

		System.out.println("FibonacciRecursive (1M iteration average): " + formatTime(
				Timing.measureAverageNanos(1_000_000, () -> generateHeat(RECURSIVE))
		));
		System.out.println("FibonacciDynamicRecursivePersistent (1M iteration average): " + formatTime(
				Timing.measureAverageNanos(1_000_000, () -> generateHeat(DYNAMIC))
		));
		System.out.println("FibonacciDynamicIterative (1M iteration average): " + formatTime(
				Timing.measureAverageNanos(1_000_000, () -> generateHeat(ITERATIVE))
		));

		System.out.println("# Parallel");
		System.out.println("FibonacciRecursiveParallel (first): " + formatTime(
				Timing.measureNanos(() -> generateHeat(PARALLEL_RECURSIVE))
		));
		System.out.println("FibonacciDynamicRecursivePersistentParallel (first): " + formatTime(
				Timing.measureNanos(() -> generateHeat(PARALLEL_DYNAMIC))
		));
		System.out.println("FibonacciRecursiveParallel (10 iteration average): " + formatTime(
				Timing.measureAverageNanos(10, () -> generateHeat(PARALLEL_RECURSIVE))
		));
		System.out.println("FibonacciDynamicRecursivePersistentParallel (1M iteration average): " + formatTime(
				Timing.measureAverageNanos(1_000_000, () -> generateHeat(PARALLEL_DYNAMIC))
		));
	}

	private static void generateHeat(Fibonacci calculator) {
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
			return Math.round(nanos) + " nanoseconds";
		}
	}
}
