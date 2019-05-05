package _4._1;

public class SquareFunction extends Function {
	public SquareFunction() {
		super();
	}

	public SquareFunction(Function predecessor) {
		super(predecessor);
	}

	@Override
	protected double apply(double x) {
		return x * x;
	}
}
