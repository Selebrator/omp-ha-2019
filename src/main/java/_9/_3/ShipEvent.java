package _9._3;

import java.util.Random;

public enum ShipEvent {
	
	NO_EVENT,
	SET_SAILS,
	STRIKE_SAILS,
	LOAD_CANNONS,
	FIRE_CANNONS,
	TURN_LEFT,
	TURN_RIGHT;

	public static ShipEvent getRandomShipEvent() {
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}
}
