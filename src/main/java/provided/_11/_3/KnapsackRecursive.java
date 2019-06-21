import java.util.Collection;

public class KnapsackRecursive extends Knapsack {

	public KnapsackRecursive(int capacity, Collection<Item> candidates) {
		super(capacity, candidates);
	}

	@Override
	public Selection pack() {
		//TODO: implement this
		return new Selection();
	}

}
