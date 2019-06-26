package _12._1.lukas;

import provided._12._1.Fibonacci;

public class FibonacciRecursive extends Fibonacci {
	@Override
	public long calculate(int n) {
		if(n == 0 || n == 1) {
			return n;
		}
		return calculate(n - 1) + calculate(n - 2);
	}
}
