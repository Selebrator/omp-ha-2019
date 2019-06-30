package _12._1.lukas;

import provided._12._1.Fibonacci;

import java.util.HashMap;
import java.util.Map;

// eats up the heap
public class FibonacciDynamicRecursivePersistentParallel extends Fibonacci {
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

		final long result;
		{
			final long[] parts = new long[2];
			Thread big = new Thread(() -> parts[0] = calculate(n - 1));
			Thread small = new Thread(() -> parts[1] = calculate(n - 2));
			big.start();
			small.start();

			try {
				big.join();
				small.join();
				result = parts[0] + parts[1];
			} catch(InterruptedException e) {
				throw new IllegalStateException(e);
			}
		}

		this.memory.put(n, result);
		return result;
	}
}
