package _5._3;

public class Person<T extends Older> implements Older<T> {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public boolean isOlder(T other) {
        return this.age > other.getAge();
    }
}
