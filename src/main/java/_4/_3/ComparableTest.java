package _4._3;

/*
 * Wenn da nicht die Vorgabe mit der Ausgabe
 * auf die Konsole wäre könnte man hierraus
 * einen schönen UnitTest machen. Wir können
 * uns aber natürlich auch schlechtes Verhalten
 * angewöhnen...
 */
public class ComparableTest {
	public static void main(String[] args) {
		ComparableInteger[] array = new ComparableInteger[5];

		for(int i = 0; i < array.length; i++) {
			array[i] = new ComparableInteger(i);
		}

		ComparableInteger minimum = (ComparableInteger) Utils.getMinimum(array);
		System.out.println(minimum.value); // Erwartet: 0
	}
}
