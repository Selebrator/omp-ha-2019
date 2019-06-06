package provided._9;

import _9._3.DrunkenPirate;
import _9._3.Ship;
import _9._3.ShipLog;

public class ShipTest {

	private static final int MAX_COMMANDS = 50;

	public static void main(String[] args) {
		Ship blackPearl = new Ship();
		blackPearl.addObserver(new ShipLog());
		Captain sparrow = new DrunkenPirate(blackPearl);
		for (int i = 0; i < MAX_COMMANDS; i++) {
			sparrow.commandShip();
		}
	}

}
