package _11._3;

import provided._11._3.Item;
import provided._11._3.Knapsack;
import provided._11._3.Selection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class KnapsackRecursive extends Knapsack {

	public KnapsackRecursive(int capacity, Collection<Item> candidates) {
		super(capacity, candidates);
	}

	@Override
	public Selection pack() {
		return this.pack(new Selection());
	}

	private Selection pack(Selection me) {
		Collection<Selection> children = new ArrayList<>();
		for(Item candidate : this.candidates) {
			if(candidate.getWeight() + me.getWeight() <= this.capacity) {
				children.add(pack(new Selection(me, candidate)));
			}
		}

		return children.stream().max(Comparator.comparingDouble(Selection::getValue)).orElse(me);
	}

}
