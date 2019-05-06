package _4._3;

public class ComparableTest {
	public static void main(String[] args) {
		ComparableInteger[] array = new ComparableInteger[5];

		for (int i=0; i<array.length; i++) {
			array[i] = new ComparableInteger(i);
		}

		ComparableInteger minimum = (ComparableInteger) Utils.getMinimum(array);
		System.out.println(minimum.value); // Erwartet: 0
	}
}
