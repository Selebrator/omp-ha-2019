package _4._2;

public class Naturals implements Sequence {
	private int last;

	@Override
	public int getNext() {
		return ++this.last;
	}
}
