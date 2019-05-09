package _5._3;

import java.util.LinkedList;

public class Group<T extends Older> {
    private LinkedList<T> members = new LinkedList<>();

    public void add(T member) {
        this.members.add(member);
    }

    public T getOldest() {
        if (members.isEmpty()) {
            return null;
        }

        T oldestMember = members.get(0);

        for (int i=1; i<members.size(); i++) {
            if (members.get(i).isOlder(oldestMember)) {
                oldestMember = members.get(i);
            }
        }
        return oldestMember;
    }
}
