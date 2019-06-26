package _12._1.lukas;

public class Timing {

	public static long measureNanos(Runnable runnable) {
		long start = System.nanoTime();
		runnable.run();
		return System.nanoTime() - start;
	}

	public static double measureAverageNanos(int iterations, Runnable runnable) {
		long start = System.nanoTime();
		for(int i = 0; i < iterations; i++) {
			runnable.run();
		}
		return (System.nanoTime() - start) / (double) iterations;
	}
}
