package _4._2;

public abstract class Filter implements Sequence {
	protected Sequence sequence;

	public Filter(Sequence sequence) {
		this.sequence = sequence;
	}
}
