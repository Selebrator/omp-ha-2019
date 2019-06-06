package _9._3;

import java.util.concurrent.ThreadLocalRandom;

public enum ShipEvent {
	
	NO_EVENT,
	SET_SAILS,
	STRIKE_SAILS,
	LOAD_CANNONS,
	FIRE_CANNONS,
	TURN_LEFT,
	TURN_RIGHT;

	public static ShipEvent getRandomShipEvent() {
		return values()[ThreadLocalRandom.current().nextInt(values().length)];
	}
}
