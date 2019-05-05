package _4._1;

public class SineFunction extends Function {
	public SineFunction() {
		super();
	}

	public SineFunction(Function predecessor) {
		super(predecessor);
	}

	@Override
	public double apply(double x) {
		return Math.sin(x);
	}
}
