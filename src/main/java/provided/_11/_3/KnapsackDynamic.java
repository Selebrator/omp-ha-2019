import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class KnapsackDynamic extends Knapsack {
	
	public KnapsackDynamic(int capacity, Collection<Item> candidates) {
		super(capacity, candidates);
	}

	@Override
	public Selection pack() {
		//TODO: implement this
		return new Selection();
	}

}
