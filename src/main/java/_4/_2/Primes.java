package _4._2;

public class Primes extends Filter {
	public Primes() {
		super(new Naturals());
		this.sequence.getNext(); // remove 1 form the sequence
	}

	@Override
	public int getNext() {
		int ret = this.sequence.getNext(); // also removes ret form the sequence
		this.sequence = new ZapMultiples(ret, this.sequence);
		return ret;
	}
}
