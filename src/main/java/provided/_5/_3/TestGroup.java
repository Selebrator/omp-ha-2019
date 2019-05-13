package provided._5._3;

import _5._3.Group;
import _5._3.Person;

public class TestGroup {
	public static void main(String[] args) {
		Group<Person> group = new Group<>();
		group.add(new Person("Alice", 25));
		group.add(new Person("Bob", 23));
		group.add(new Person("Carl", 26));
		System.out.println(group.getOldest().getName());
	}
}