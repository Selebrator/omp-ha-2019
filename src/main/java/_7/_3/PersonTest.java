package _7._3;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class Person implements Serializable {
    private String firstname;
    private String lastname;
    private transient String sortname;

    public Person() {
    }

    public Person(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
        updateSortname();
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
        updateSortname();
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
        updateSortname();
    }

    public String getSortname() {
        return sortname;
    }

    public void updateSortname() {
        sortname = lastname + firstname;
    }

    @Override
    public String toString() {
        return firstname + " " + lastname + " (" + sortname + ")";
    }


    public static List<Person> load(String filename) throws IOException {
        try(DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)))) {
            return loadAll(in);
        }
    }

    public static List<Person> loadAll(DataInputStream in) throws IOException {
        int size = in.readInt();
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            persons.add(load(in));
        }
        return persons;
    }

    public static Person load(DataInputStream in) throws IOException {
        return new Person(in.readUTF(), in.readUTF());
    }

    public static void save(String filename, List<Person> list) throws IOException {
        try(DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filename)))) {
            saveAll(out, list);
        }
    }

    public static void saveAll(DataOutputStream out, List<Person> persons) throws IOException {
        out.writeInt(persons.size());
        for (Person person : persons) {
            save(out, person);
        }
    }

    public static void save(DataOutputStream out, Person person) throws IOException {
        out.writeUTF(person.getFirstname());
        out.writeUTF(person.getLastname());
    }


    @SuppressWarnings("unchecked")
    public static List<Person> unserialize(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(filename)))) {
            List<Person> people = (List<Person>) in.readObject();
            people.forEach(Person::updateSortname);
            return people;
        }
    }

    public static void serialize(String filename, List<Person> persons) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(filename)))) {
            out.writeObject(persons);
        }
    }
}

public class PersonTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Willy", "Wonka"));
        persons.add(new Person("Charlie", "Bucket"));
        persons.add(new Person("Grandpa", "Joe"));
        System.out.println(persons);

        Person.save("persons.sav", persons);
        persons = Person.load("persons.sav");
        System.out.println(persons);
        Person.serialize("persons.ser", persons);
        persons = Person.unserialize("persons.ser");
        System.out.println(persons);
    }

}
