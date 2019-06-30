package _12._1.lukas;

import provided._12._1.Fibonacci;

import java.util.HashMap;
import java.util.Map;

// uses recursion and dynamic programing with persistent memory
public class FibonacciDynamicRecursivePersistent extends Fibonacci {
	private final Map<Integer, Long> memory = new HashMap<>();

	{
		this.memory.put(0, 0L);
		this.memory.put(1, 1L);
	}


	@Override
	public long calculate(int n) {
		if(n < 0) {
			throw new IllegalArgumentException("n must not be negative. Negafibonacci numbers are not supported.");
		}
		if(this.memory.containsKey(n)) {
			return this.memory.get(n);
		}

		long result = calculate(n - 1) + calculate(n - 2);
		this.memory.put(n, result);
		return result;
	}
}
