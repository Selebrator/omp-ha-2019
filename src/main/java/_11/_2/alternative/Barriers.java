package _11._2.alternative;

public class Barriers {

	public static final int NUMBER_OF_RUNNERS = 3;

	public static void main(String[] args) {
		NumberRunner[] runners = new NumberRunner[NUMBER_OF_RUNNERS];
		for(int i = 0; i < NUMBER_OF_RUNNERS; i++) {
			runners[i] = new NumberRunner(i);
		}
		for(int i = 0; i < NUMBER_OF_RUNNERS; i++) {
			runners[i].start();
		}
	}
}
