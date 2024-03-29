package _12._1.lukas;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import provided._12._1.Fibonacci;

import java.util.Map;

public class FibonacciTest {
	private static final Map<Integer, Long> NUMBERS = Map.ofEntries(
			Map.entry(3, 2L),
			Map.entry(5, 5L),
			Map.entry(8, 21L),
			Map.entry(12, 144L),
			Map.entry(9, 34L),
			Map.entry(18, 2584L),
			Map.entry(15, 610L),
			Map.entry(10, 55L),
			Map.entry(7, 13L),
			Map.entry(11, 89L),
			Map.entry(20, 6765L)
	);

	@Test
	void recursive() {
		Fibonacci calculator = new FibonacciRecursive();
		NUMBERS.forEach((n, fn) -> Assertions.assertEquals(fn.longValue(), calculator.calculate(n)));
	}

	@Test
	void recursiveParallel() {
		Fibonacci calculator = new FibonacciRecursiveParallel();
		NUMBERS.forEach((n, fn) -> Assertions.assertEquals(fn.longValue(), calculator.calculate(n)));
	}

	@Test
	void dynamicRecursivePersistent() {
		Fibonacci calculator = new FibonacciDynamicRecursivePersistent();
		NUMBERS.forEach((n, fn) -> Assertions.assertEquals(fn.longValue(), calculator.calculate(n)));
	}

	@Test
	void dynamicRecursivePersistentParallel() {
		Fibonacci calculator = new FibonacciDynamicRecursivePersistentParallel();
		NUMBERS.forEach((n, fn) -> Assertions.assertEquals(fn.longValue(), calculator.calculate(n)));
	}

	@Test
	void dynamicIterative() {
		Fibonacci calculator = new FibonacciDynamicIterative();
		NUMBERS.forEach((n, fn) -> Assertions.assertEquals(fn.longValue(), calculator.calculate(n)));
	}

	@Test
	void doubling() {
		Fibonacci calculator = new FibonacciDoubling();
		NUMBERS.forEach((n, fn) -> Assertions.assertEquals(fn.longValue(), calculator.calculate(n)));
	}
}
