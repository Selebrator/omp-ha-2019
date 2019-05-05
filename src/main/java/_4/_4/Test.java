package _4._4;

import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		Office office = new Office(
				Arrays.asList(new Chair()),
				Arrays.asList(new Desk()),
				Arrays.asList(new Chair())
		);
	}
}
