package _5._1;

import provided.IO;

class Util {

	// liefert die kleinste Zahl des uebergebenen Arrays
	public static int minimum(int[] values) throws NullPointerException, EmptyArrayException {
		//NPE finde ich hilfreicher als alles andere weil ich dann weiß das irgendow null übergeben wurde.
		if(values.length == 0) {
			throw new EmptyArrayException("Cannot get the minimum of nothing");
		}
		int min = values[0];
		for(int i = 1; i < values.length; i++) {
			if(values[i] < min) {
				min = values[i];
			}
		}
		return min;
	}

	// konvertiert den uebergebenen String in einen int-Wert
	public static int toInt(String str) throws NullPointerException,
			EmptyStringException,
			IllegalNumberException,
			IllegalDigitException,
			NumberOverflowException {
		//NPE finde ich hilfreicher als alles andere weil ich dann weiß das irgendow null übergeben wurde.
		if(str.isEmpty()) {
			throw new EmptyStringException();
		}
		int result = 0, factor = 1;
		char ch = str.charAt(0);
		if((ch == '+' || ch == '-') && str.length() == 1) {
			throw new IllegalNumberException(ch + " is not a number");
		}
		int i = 1;
		switch(ch) {
			case '-':
				factor = -1;
				break;
			case '+':
				factor = 1;
				break;
			default:
				i = 0;
		}
		for(; i < str.length(); i++) {
			ch = str.charAt(i);
			if(!('0' <= ch && ch <= '9')) {
				throw new IllegalDigitException(ch + " is not a digit in base 10");
			}
			int ziffer = ch - '0';
			int newResult = result * 10 + ziffer;
			if(newResult < result) {
				throw new NumberOverflowException();
			}
			result = newResult;
		}
		return factor * result;
	}

	// liefert die Potenz von zahl mit exp,
	// also zahl "hoch" exp (number to the power of exp)
	public static long power(long number, int exp) throws UnsupportedOperationException, UndefinedValueException {
		if(exp < 0) {
			throw new UnsupportedOperationException("not implemented for negative exponents");
		}
		if(exp == 0) {
			if(number == 0) {
				throw new UndefinedValueException("0^0 not defined");
			}
			return 1L;
		}
		return number * Util.power(number, exp - 1);
	}
}

public class UtilTest {

	// Testprogramm
	public static void main(String[] args) {
		try {
			String eingabe = IO.readString("Zahl: ");
			int zahl = Util.toInt(eingabe);
			System.out.println(zahl + " hoch " + zahl + " = "
					+ Util.power(zahl, zahl));
		} catch(NullPointerException | EmptyStringException | IllegalNumberException | IllegalDigitException | NumberOverflowException | UnsupportedOperationException | UndefinedValueException e) {
			err(e);
		}
		System.out.println(Util.minimum(new int[]{ 1, 6, 4, 7, -3, 2 }));
		try {
			System.out.println(Util.minimum(new int[0]));
		} catch(EmptyArrayException e) {
			err(e);
		}
		try {
			System.out.println(Util.minimum(null));
		} catch(NullPointerException e) {
			err(e);
		}
	}

	private static void err(Exception e) {
		System.err.println(e.getClass().getSimpleName() + (e.getMessage() != null ? ": " + e.getMessage() : ""));
	}
}

