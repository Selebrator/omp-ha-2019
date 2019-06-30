package _12._1.lukas;

import provided._12._1.Fibonacci;

// rest in peace ram
public class FibonacciRecursiveParallel extends Fibonacci {
	@Override
	public long calculate(int n) {
		if(n < 0) {
			throw new IllegalArgumentException("n must not be negative. Negafibonacci numbers are not supported.");
		}
		if(n == 0 || n == 1) {
			return n;
		}

		final long[] parts = new long[2];
		Thread big = new Thread(() -> parts[0] = calculate(n - 1));
		Thread small = new Thread(() -> parts[1] = calculate(n - 2));
		big.start();
		small.start();

		try {
			big.join();
			small.join();
			return parts[0] + parts[1];
		} catch(InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}
}
