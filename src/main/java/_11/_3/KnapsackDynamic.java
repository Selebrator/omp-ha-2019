package _11._3;

import provided._11._3.Item;
import provided._11._3.Knapsack;
import provided._11._3.Selection;

import java.util.*;

public class KnapsackDynamic extends Knapsack {
	
	public KnapsackDynamic(int capacity, Collection<Item> candidates) {
		super(capacity, candidates);
	}

	@Override
	public Selection pack() {
		return this.pack(new Selection(), new HashMap<>());
	}

	private Selection pack(Selection parent, Map<Integer, Selection> memory) {
		return memory.computeIfAbsent(parent.getWeight(), weight -> {
			final Collection<Selection> children = new ArrayList<>();
			for(Item candidate : this.candidates) {
				if(parent.getWeight() + candidate.getWeight() <= this.capacity) {
					children.add(pack(new Selection(parent, candidate), memory));
				}
			}

			return children.stream()
					.max(Comparator.comparingDouble(Selection::getValue))
					.orElse(parent);
		});
	}

}
