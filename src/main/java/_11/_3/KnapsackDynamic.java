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

	private Selection pack(Selection me, Map<Integer, Selection> mem) {
		return mem.computeIfAbsent(me.getWeight(), weight -> {
			Collection<Selection> children = new ArrayList<>();
			for(Item candidate : this.candidates) {
				if(candidate.getWeight() + me.getWeight() <= this.capacity) {
					children.add(pack(new Selection(me, candidate), mem));
				}
			}

			return children.stream().max(Comparator.comparingDouble(Selection::getValue)).orElse(me);
		});
	}

}
