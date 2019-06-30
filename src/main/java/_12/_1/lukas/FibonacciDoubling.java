package _12._1.lukas;

import provided._12._1.Fibonacci;

/*
 * F(2n) = F(n) * (2*F(n+1) - F(n)).
 * F(2n+1) = F(n+1)^2 + F(n)^2.
 *
 * O(log(n))
 */
public class FibonacciDoubling extends Fibonacci {
	@Override
	public long calculate(int n) {
		if(n < 0) {
			throw new IllegalArgumentException("n must not be negative. Negafibonacci numbers are not supported.");
		}
		long a = 0L;
		long b = 1L;
		for(int bit = Integer.highestOneBit(n); bit != 0; bit >>>= 1) {
			long aTemp = a * ((2 * b) - a);
			long bTemp = a * a + b * b;
			a = aTemp;
			b = bTemp;

			if((n & bit) != 0) {
				long c = a + b;
				a = b;
				b = c;
			}
		}
		return a;
	}
}
