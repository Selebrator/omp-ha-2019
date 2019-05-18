package _5._2.car;

import java.util.HashSet;
import java.util.Set;

public class Car implements CarComponent {
	private String name;
	private final Set<CarComponent> components = new HashSet<>();

	public Car(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Set<CarComponent> getComponents() {
		return this.components;
	}

	@Override
	public String toString() {
		if(this.components.isEmpty()) {
			return this.name;
		}
		return this.name + ": " + this.components;
	}

	public abstract static class CarPart implements CarComponent {
		private String name;
		private final Set<CarComponent> components = new HashSet<>();

		public CarPart(String name) {
			this.name = name;
		}

		@Override
		public String getName() {
			return this.name;
		}

		@Override
		public Set<CarComponent> getComponents() {
			return this.components;
		}

		@Override
		public String toString() {
			if(this.components.isEmpty()) {
				return this.name;
			}
			return this.name + ": " + this.components;
		}
	}
}
