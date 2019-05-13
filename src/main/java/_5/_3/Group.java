package _5._3;

import java.util.LinkedList;
import java.util.List;

public class Group<T extends Older<T>> {
	private List<T> members = new LinkedList<>();

	public void add(T member) {
		this.members.add(member);
	}

	public T getOldest() {
		if(this.members.isEmpty()) {
			return null;
		}

		T oldest = this.members.get(0);
		for(int i = 1; i < this.members.size(); i++) {
			if(this.members.get(i).isOlder(oldest)) {
				oldest = this.members.get(i);
			}
		}
		return oldest;
	}
}
