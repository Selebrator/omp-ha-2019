package _11._2.alternative;

public class Checkpoint {

	private final Object lock = new Object();
	private int groupSize;
	private int gathered = 0;

	public Checkpoint(int groupSize) {
		this.groupSize = groupSize;
	}

	/**
	 * Blocks until called by {@link #groupSize} different Threads,
	 * then wakes all of them up again.
	 */
	public void gather() throws InterruptedException {
		synchronized(this.lock) {
			if(++this.gathered != this.groupSize) {
				this.lock.wait();
			} else {
				this.gathered = 0;
				System.out.println("Group gathered");
				this.lock.notifyAll();
			}
		}
	}
}