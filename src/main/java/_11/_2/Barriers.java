package _11._2;

public class Barriers {

	private final static int NUMBER = 3;

	public static void main(String[] args) {
		Counter obj = new Counter(NUMBER);
		NumberRunner[] runner = new NumberRunner[NUMBER];
		for (int i = 0; i < NUMBER; i++) {
			runner[i] = new NumberRunner(i, obj);
		}
		for (int i = 0; i < NUMBER; i++) {
			runner[i].start();
		}
	}

}

class Counter {
	private final int numb; // Anzahl Threads
	private int numberIn = 0; // Anzahl der Threads an Sperre
	private int numberOut; // Anzahl der laufenden Threads

	public Counter(int number) {
		this.numb = number;
		this.numberOut = number;
	}

	public int getMax() { return this.numb; }

	public int getIn() {
		return this.numberIn;
	}

	public int getOut() { return this.numberOut; }

	public void resetIn() { this.numberIn = 0; }

	public void resetOut() { this.numberOut = 0; }

	public void incrementIn() { this.numberIn++; }

	public void incrementOut() { this.numberOut++; }

}

class NumberRunner extends Thread {

	private int number;
	private final Counter obj;

	public NumberRunner(int n, Counter obj) {
		this.number = n;
		this.obj = obj;
	}

	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {

			System.out.println("Thread " + number + ": " + i);

			if (i%10 == 0 && i != 0) {
				synchronized (obj) {
					try {
						// warte, falls andere Threads von vorheriger Sperre noch nicht wieder gestartet sind
						while (obj.getOut() < obj.getMax()) {
							obj.wait();
						}

						// Thread kommt an aktueller Sperre an
						obj.incrementIn();

						// letzter Thread, der ankommt, benachrichtigt alle, dass es weiter gehen kann
						if (obj.getIn() >= obj.getMax()) {
							obj.resetOut();
							obj.notifyAll();
						}

						// Threads warten, bis alle angekommen sind
						while (obj.getIn() < obj.getMax()) {
							obj.wait();
						}

						// Thread startet wieder
						obj.incrementOut();

						// der letzte Thread, der startet, benachrichtigt alle, dass die naechste Sperre freigegeben ist
						if (obj.getOut() == obj.getMax()) {
							obj.resetIn();
							obj.notifyAll();
						}

					} catch (InterruptedException e) {
					}
				}
			}
		}
	}
}
