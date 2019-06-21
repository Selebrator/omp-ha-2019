package _11._3;

import provided._11._3.Item;
import provided._11._3.Knapsack;
import provided._11._3.Selection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class KnapsackGreedy extends Knapsack {

	// descending
	protected final List<Item> orderedCandidates;

	public KnapsackGreedy(int capacity, Collection<Item> candidates) {
		super(capacity, candidates);
		this.orderedCandidates = new ArrayList<>(this.candidates);
		this.orderedCandidates.sort(Comparator.comparing(Item::getValue).reversed());
	}

	@Override
	public Selection pack() {
		Selection selection = new Selection();
		for(Item candidate : this.orderedCandidates) {
			while(candidate.getWeight() + selection.getWeight() <= this.capacity) {
				selection = new Selection(selection, candidate);
			}
		}
		return selection;
	}

}
