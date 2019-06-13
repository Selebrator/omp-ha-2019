package _10._4.lukas;

// ordered by creation time, deadlocks if any thread in the chain is interrupted.
public class NameOutputBusyWaiting extends Thread {

	private static final Object LOCK = new Object();

	private static int counter = 0;
	private static int total = 0;

	private final int number;

	public NameOutputBusyWaiting() {
		synchronized(LOCK) {
			this.number = total;
			total++;
		}
	}

	@Override
	public void run() {
		while(!this.isInterrupted()) {
			synchronized(LOCK) {
				if(this.number == counter) {
					System.out.println(this.getName());
					counter = (counter + 1) % total;
				}
			}
		}
	}
}
