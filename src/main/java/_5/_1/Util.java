package _5._1;

class Util {

	// liefert die kleinste Zahl des uebergebenen Arrays
	public static int minimum(int[] values) throws IllegalArgumentException {
		if (values.length <= 1 || values == null) {
			throw new IllegalArgumentException("The given array has not enough elements (<=1) or is null");
		}
		int min = values[0];
		for (int i = 1; i < values.length; i++) {
			if (values[i] < min) {
				min = values[i];
			}
		}
		return min;
	}

	// konvertiert den uebergebenen String in einen int-Wert
	public static int toInt(String str) {
		int result = 0, factor = 1;
		char ch = str.charAt(0);
		switch (ch) {
			case '-':
				factor = -1;
				break;
			case '+':
				factor = 1;
				break;
			default:
				result = ch - '0';
		}
		for (int i = 1; i < str.length(); i++) {
			ch = str.charAt(i);
			int ziffer = ch - '0';
			result = result * 10 + ziffer;
		}
		return factor * result;
	}

	// liefert die Potenz von zahl mit exp,
	// also zahl "hoch" exp (number to the power of exp)
	public static long power(long number, int exp) throws IllegalArgumentException {
		if(number == 0L && exp < 0){
			throw new IllegalArgumentException("Division by zero");
		}
		if (exp == 0) {
			return 1L;
		}
		return number * Util.power(number, exp - 1);
	}
}