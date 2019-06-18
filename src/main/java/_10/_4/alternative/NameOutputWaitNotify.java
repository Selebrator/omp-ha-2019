package _10._4.alternative;

// ordered by creation time, deadlocks if any thread in the chain is interrupted.
public class NameOutputWaitNotify extends Thread {

	private static final Object LOCK = new Object();

	private static int counter = 0;
	private static int total = 0;

	private final int number;

	public NameOutputWaitNotify() {
		synchronized(LOCK) {
			this.number = total;
			total++;
		}
	}

	@Override
	public void run() {
		while(!this.isInterrupted()) {
			synchronized(LOCK) {
				while(this.number != counter) {
					try {
						LOCK.wait();
					} catch(InterruptedException e) {
						System.out.println(this.getName() + " was interrupted");
					}
				}
				System.out.println(this.getName());
				counter = (counter + 1) % total;
				LOCK.notifyAll();
			}
		}
	}
}
