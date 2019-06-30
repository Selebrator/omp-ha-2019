package _12._1.lukas;

import provided._12._1.Fibonacci;

// iterative, dynamic programing with minimal memory usage
public class FibonacciDynamicIterative extends Fibonacci {
	@Override
	public long calculate(int n) {
		if(n < 0) {
			throw new IllegalArgumentException("n must not be negative. Negafibonacci numbers are not supported.");
		}
		long a = 0L, b = 1L, c;
		for(int i = 0; i < n; i++) {
			c = a + b;
			a = b;
			b = c;
		}
		return a;
	}
}
