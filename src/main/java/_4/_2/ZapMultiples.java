package _4._2;

public class ZapMultiples extends Filter {
	private final int divisor;

	public ZapMultiples(int divisor, Sequence sequence) {
		super(sequence);
		if(divisor < 2) {
			throw new IllegalArgumentException("divisor must be >= 2");
		}
		this.divisor = divisor;
	}

	@Override
	public int getNext() {
		int next = this.sequence.getNext();
		return next % divisor == 0 ? this.getNext() : next;
	}
}
