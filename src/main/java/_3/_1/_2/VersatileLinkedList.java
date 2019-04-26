package _3._1._2;

public class VersatileLinkedList extends LinkedStringList {

	public void add(int value) {
		String stringValue = Integer.toString(value);
		add(stringValue);
	}

	public void add(boolean value) {
		if(value) {
			add("yes");
		} else {
			add("no");
		}
	}

	public void add(LinkedStringList list) {
		for(int i = 0; i < list.size(); i++) {
			add(list.get(i));
		}
	}

	public void add(LinkedStringList list, int start, int end) {
		if(!(start > end || start < 0 || end > list.size())) {
			for(int i = start; i < end; i++) {
				add(list.get(i));
			}
		}
	}

	public VersatileLinkedList reverse() {
		VersatileLinkedList list = new VersatileLinkedList();

		for(int i = size() - 1; i >= 0; i--) {
			list.add(get(i));
		}

		return list;
	}

	public boolean equals(VersatileLinkedList list) {
		if(this.size() != list.size()) {
			return false;
		} else
			for(int i = 0; i < list.size(); i++) {
				if(!list.get(i).equals(this.get(i))) {
					return false;
				}
			}
		return true;
	}


	public static void main(String[] args) {
		VersatileLinkedList list1 = new VersatileLinkedList();
		VersatileLinkedList list2 = new VersatileLinkedList();
		list1.add(1);
		list1.add(true);
		list1.add("2");
		System.out.println(list1.size()); // Erwartete Ausgabe: 3
		list2.add(1);
		list2.add(true);
		list2.add("2");
		if(list1.equals(list2)) {
			System.out.println("Listen sind gleich");
		} else {
			System.out.println("Listen sind ungleich");
		}
		// Erwartete Ausgabe: Listen sind gleich

		list2 = list2.reverse();
		if(list1.equals(list2)) {
			System.out.println("Listen sind gleich");
		} else {
			System.out.println("Listen sind ungleich");
		}
		// Erwartete Ausgabe: Listen sind ungleich

		for(int i = 0; i < list1.size(); i++) {
			System.out.println(list1.get(i));
		}
		// Erwartete Ausgabe: 1, yes, 2

		list1.add(list2);
		System.out.println(list1.size()); // Erwartete Ausgabe: 6
		for(int i = 0; i < list1.size(); i++) {
			System.out.println(list1.get(i));
		}
		// Erwartete Ausgabe: 1, yes, 2, 2, yes, 1

		if(list1.equals(list2)) {
			System.out.println("Listen sind gleich");
		} else {
			System.out.println("Listen sind ungleich");
		}
		// Erwartete Ausgabe: Listen sind ungleich

		list1.add(list2, 1, 2);
		for(int i = 0; i < list1.size(); i++) {
			System.out.println(list1.get(i));
		}
		// Erwartete Ausgabe: 1, yes, 2, 2, yes, 1, yes

		list1.add(list2, 3, 2);
		for(int i = 0; i < list1.size(); i++) {
			System.out.println(list1.get(i));
		}
		// Erwartete Ausgabe: 1, yes, 2, 2, yes, 1, yes
	}
}
