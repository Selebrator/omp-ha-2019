package _12._1.lukas;

import provided._12._1.Fibonacci;

import java.util.HashMap;
import java.util.Map;

public class FibonacciDynamic extends Fibonacci {
	private static final Map<Integer, Long> memory = new HashMap<>();

	static {
		memory.put(0, 0L);
		memory.put(1, 1L);
	}


	@Override
	public long calculate(int n) {
		if(n < 0) {
			throw new IllegalArgumentException("n must not be negative. Negafibonacci numbers are not supported.");
		}
		if(memory.containsKey(n)) {
			return memory.get(n);
		}

		long result = calculate(n - 1) + calculate(n - 2);
		memory.put(n, result);
		return result;
	}
}
