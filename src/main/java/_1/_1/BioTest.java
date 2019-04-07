package _1._1;

public class BioTest {
    public static void main(String[] args) {
        Plant grass = new Plant("Grass", "is green");
        Plant berry = new Plant("Berry", "is red");
        Animal zebra = new Animal("Zebra");
        zebra.addFood(grass);
        Animal lion = new Animal("Lion");
        lion.addFood(zebra);
        Animal bear = new Animal("Bear");
        bear.addFood(berry);
        Animal fish = new Animal("Fish");
        bear.addFood(fish);

        System.out.println(getFoodClassDescription(zebra));
        System.out.println(getFoodClassDescription(lion));
        System.out.println(getFoodClassDescription(bear));
    }

    private static String getFoodClassDescription(Animal animal) {
        if(animal.isHerbivore()) {
            return animal.getName() + " is a herbivore";
        } else if(animal.isCarnivore()) {
            return animal.getName() + " is a carnivore";
        } else if(animal.isOmnivore()) {
            return animal.getName() + " is an omnivore";
        } else {
            return animal.getName() + " is water fasting";
        }
    }
}
