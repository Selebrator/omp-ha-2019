package _11._3;

import provided._11._3.Item;
import provided._11._3.Knapsack;
import provided._11._3.Selection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class KnapsackGreedy extends Knapsack {

	private static final Comparator<Item> DESCENDING_ITEM_VALUE_COMPARATOR
			= Comparator.comparing(Item::getValue).reversed();

	public KnapsackGreedy(int capacity, Collection<Item> candidates) {
		super(capacity, candidates);
	}

	@Override
	public Selection pack() {
		final List<Item> orderedCandidates = new ArrayList<>(this.candidates);
		orderedCandidates.sort(DESCENDING_ITEM_VALUE_COMPARATOR);

		/*
		 * Fügt das wertvollste Item hinzu, solange es noch passt, geht
		 * dann zum nächst wertvolleren Item über und wiederholt den Vorgang
		 * so lange bis kein Item mehr passt.
		 */
		Selection selection = new Selection();
		for(Item candidate : orderedCandidates) {
			while(selection.getWeight() + candidate.getWeight() <= this.capacity) {
				selection = new Selection(selection, candidate);
				/*
				 * Der Speicher leidet hier sehr,
				 * weil Selection#add(Item) private ist.
				 */
			}
		}
		return selection;
	}
}
