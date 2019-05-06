package _4._3;

public class Utils {
	/**
	 * Returns the smallest element
	 *
	 * @throws NullPointerException           if elements == null
	 * @throws ArrayIndexOutOfBoundsException if elements.length == 0
	 */
	public static Comparable getMinimum(Comparable[] elements) throws NullPointerException, IndexOutOfBoundsException {
		Comparable min = elements[0]; // assume the first element is the smallest
		for(int i = 1; i < elements.length; i++) {
			Comparable candidate = elements[i];
			if(min.compareTo(candidate) > 0) {
				min = candidate;
			}
		}
		return min;
	}

	public static void main(String[] args) {
		ComparableInteger[] array = new ComparableInteger[5];

		for (int i=0; i<array.length; i++) {
			array[i] = new ComparableInteger(i);
		}

		ComparableInteger minimum = (ComparableInteger) getMinimum(array);
		System.out.println(minimum.value); // Erwartet: 0

	}
}
