package _11._2.alternative;

import java.util.concurrent.atomic.AtomicInteger;

public class Checkpoint {

	private static AtomicInteger GATHERED = new AtomicInteger(0);

	private final Object lock = new Object();
	private int groupSize;

	public Checkpoint(int groupSize) {
		this.groupSize = groupSize;
	}

	/**
	 * Blocks until called by {@link #groupSize} different Threads,
	 * then wakes all of them up again.
	 */
	public void gather() throws InterruptedException {
		synchronized(this.lock) {
			if(GATHERED.incrementAndGet() != this.groupSize) {
				this.lock.wait();
			} else {
				GATHERED.set(0);
				System.out.println("Group gathered");
				this.lock.notifyAll();
			}
		}
	}
}