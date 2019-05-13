package _5._1;


import provided.IO;

public class UtilTest {

	// Testprogramm
	public static void main(String[] args) {
		String eingabe = IO.readString("Zahl: ");
		int zahl = Util.toInt(eingabe);
		try {
			System.out.println(zahl + " hoch " + zahl + " = "
					+ Util.power(zahl, zahl));
		}catch(IllegalArgumentException exc){
			System.out.println("A exception has occurred: Division by zero");
		}
		try {
			System.out.println(Util.minimum(new int[]{1, 6, 4, 7, -3, 2}));
			System.out.println(Util.minimum(new int[0]));
			System.out.println(Util.minimum(null));
		} catch(IllegalArgumentException exc){
			System.out.println("Array has not enough elements: <2");
		}


	}
}


