package _4._3;

public class ComparableInteger extends Integer implements Comparable {
	public ComparableInteger(int value) {
		super(value);
	}

	/**
	 * Compared this and other
	 * Returns -1 if this  < other,
	 *          0 if this == other,
	 *          1 if this  > other
	 *
	 * @throws NullPointerException if other == null
	 * @throws ClassCastException   if !(other instanceof Integer)
	 * @see Comparable#compareTo(Comparable)
	 */
	@Override
	public int compareTo(Comparable other) {
		int o = ((Integer) other).value;
		int t = this.value;
		return t < o ? -1 : t == o ? 0 : 1;
	}
}
