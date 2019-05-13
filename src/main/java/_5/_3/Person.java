package _5._3;

public class Person implements Older<Person> {
	private String name;
	private int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public boolean isOlder(Person other) {
		return this.getAge() > other.getAge();
	}

	public int getAge() {
		return this.age;
	}

	public String getName() {
		return this.name;
	}
}
