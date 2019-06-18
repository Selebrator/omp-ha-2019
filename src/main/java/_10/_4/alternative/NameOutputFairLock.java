package _10._4.alternative;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// ordered by least recent access since creation
public class NameOutputFairLock extends Thread {

	private static final Lock LOCK = new ReentrantLock(true);

	@Override
	public void run() {
		while(!this.isInterrupted()) {
			LOCK.lock();
			try {
				System.out.println(this.getName());
			} finally {
				LOCK.unlock();
			}
		}
	}
}
