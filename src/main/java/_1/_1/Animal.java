package _1._1;

import java.util.*;
import java.util.stream.Collectors;

public class Animal {
	private String name;
	private Plant[] plantFoods; //null if empty
	private Animal[] animalFoods; //null if empty

	public Animal(String name) {
		this.name = name;
	}

	/**
	 * Gives the animal the ability to eat a new plant
	 */
	public void addFood(Plant food) {
		if(this.plantFoods == null) {
			this.plantFoods = new Plant[10];
		}
		for(int i = 0; i < this.plantFoods.length; i++) {
			if(this.plantFoods[i] == null) {
				this.plantFoods[i] = food;
			}
		}
	}

	/**
	 * Gives the animal the ability to eat a new animal
	 */
	public void addFood(Animal food) {
		if(this.animalFoods == null) {
			this.animalFoods = new Animal[10];
		}
		for(int i = 0; i < this.animalFoods.length; i++) {
			if(this.animalFoods[i] == null) {
				this.animalFoods[i] = food;
			}
		}
	}

	//collects all non-null entries from an array in a set
	private static <T> Set<T> nonNullEntries(T[] array) {
		if(array == null) {
			return new HashSet<>();
		}
		return Arrays.stream(array)
				.filter(Objects::nonNull)
				.collect(Collectors.toSet());
	}

	/**
	 * Provides read-only access to the plants this animal eats.
	 */
	public Collection<Plant> getPlantFoods() {
		return nonNullEntries(this.plantFoods);
	}

	/**
	 * Provides read-only access to the animals this animal eats.
	 */
	public Collection<Animal> getAnimalFoods() {
		return nonNullEntries(this.animalFoods);
	}

	/*
	 * Es gibt keine setter für die Nahrung, weil ich dann keine Annahmen mehr
	 * über die beiden Arrays machen könnte, ohne dem Anwender alle implementation details
	 * zu verraten und mich darauf zu verlassen das sie eingehalten werden.
	 *
	 * Das ist zwar zu diesem Zeitpunkt kein Problem, würde es aber erschweren
	 * neue Funktionen hinzuzufügen.
	 *
	 * Lieber würde ich eine andere Datenstruktur wie Listen oder Sets verwenden,
	 * aber leider verbietet die Aufgabenstellung dies.
	 */

	/**
	 * @return true if this animal can eat at least one plant.
	 */
	/*
	 * Könnte private sein, aber ich habe mich dagegen entschieden weil ich diese Methoden
	 * für ähnlich praktisch wie isCarnivore(), isOmnivore() und isHerbivore() halte.
	*/
	public boolean eatsPlants() {
		return this.plantFoods != null;
	}

	/**
	 * @return true if this animal can eat at least one animal.
	 */
	public boolean eatsAnimals() {
		return this.animalFoods != null;
	}

	/**
	 * @return true if this animal can only eat animals but no plants.
	 */
	public boolean isCarnivore() {
		return this.eatsAnimals() && !this.eatsPlants();
	}

	/**
	 * @return true if this animal can eat plants and animals
	 */
	public boolean isOmnivore() {
		return this.eatsAnimals() && this.eatsPlants();
	}

	/**
	 * @return true if this animal can only eat plants but no animals.
	 */
	public boolean isHerbivore() {
		return !this.eatsAnimals() && this.eatsPlants();
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
