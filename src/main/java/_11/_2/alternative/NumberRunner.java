package _11._2.alternative;

public class NumberRunner extends Thread {

	private static final Checkpoint CHECKPOINT = new Checkpoint(Barriers.NUMBER_OF_RUNNERS);

	private final int id;

	public NumberRunner(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		for(int i = 0; i < 1000; i++) {
			if(i % 10 == 0) {
				try {
					CHECKPOINT.gather();
				} catch(InterruptedException e) {
					throw new IllegalStateException("Group can never be complete again, because one of the members got lost", e);
				}
			}

			System.out.println("Thread " + this.id + ": " + i);
		}
	}
}
