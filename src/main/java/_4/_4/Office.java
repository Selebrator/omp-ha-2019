package _4._4;

import java.util.List;

public class Office extends Room {
	private List<Desk> desks;
	private List<Chair> chairs;

	public Office(List<Furniture> furniture, List<Desk> desks, List<Chair> chairs) {
		super(furniture);
		if(desks.isEmpty()) {
			throw new IllegalArgumentException("An Office must have at least 1 Desk");
		}
		this.desks = desks;
		if(chairs.isEmpty()) {
			throw new IllegalArgumentException("An Office must have at least 1 Chair");
		}
		this.chairs = chairs;
	}
}
