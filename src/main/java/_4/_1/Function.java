package _4._1;

public abstract class Function {

	protected Function predecessor;

	public Function() {
		this.predecessor = null;
	}

	public Function(Function predecessor) {
		this.predecessor = predecessor;
	}

	public double calculate(double x) {
		if(this.predecessor == null) {
			return this.apply(x);
		}
		return this.predecessor.calculate(x);
	}

	protected abstract double apply(double x);
}
