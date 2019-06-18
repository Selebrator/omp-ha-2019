package _10._4.alternative;

public abstract class NameOutput {

	private NameOutput() {
	}

	public static void main(String[] args) {
		final int n = 3;
		for(int i = 0; i < n; i++) {
			new NameOutputFairLock().start();
		}
	}
}
