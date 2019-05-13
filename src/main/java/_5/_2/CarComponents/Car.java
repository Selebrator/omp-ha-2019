package _5._2.CarComponents;

public class Car implements CarComponent {
    private String name;
    private CarComponent[] components;

    public Car(String name, CarComponent[] components) {
        this.name = name;
        this.components = components;
    }

    public String getName() {
        return name;
    }

    public CarComponent[] getComponents() {
        return components;
    }
}
