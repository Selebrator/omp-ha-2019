import java.util.Collection;

public class KnapsackGreedy extends Knapsack {

	public KnapsackGreedy(int capacity, Collection<Item> candidates) {
		super(capacity, candidates);
	}

	@Override
	public Selection pack() {
		//TODO: implement this
		return new Selection();
	}

}
