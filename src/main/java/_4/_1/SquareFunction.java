package _4._1;

public class SquareFunction extends Function {
	public SquareFunction() {
		super();
	}

	public SquareFunction(Function inner) {
		super(inner);
	}

	@Override
	public double apply(double x) {
		return x * x;
	}
}
