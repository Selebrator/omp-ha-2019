package _4._1;

public abstract class Function {

	/**
	 * For functions f and g,
	 * g is the inner function of f in f(g(x))
	 */
	protected Function inner;

	public Function() {
		this.inner = null;
	}

	public Function(Function inner) {
		this.inner = inner;
	}

	/**
	 * <b>Note</b>: This method should probably not be overridden.
	 * <p>
	 * Computes the value of this (nested) function for x.
	 * <p>
	 * If this function is f and it's inner function is g,
	 * then compute(x) returns f(g(x))
	 *
	 * @see #apply(double)
	 */
	public double calculate(double x) {
		if(this.inner == null) {
			return this.apply(x);
		}
		return this.inner.calculate(x);
	}

	/**
	 * Computes the value of this function for x
	 * <p>
	 * If this function is f and it's inner function is g,
	 * then apply(x) returns f(x)
	 */
	/*
	 * protected to allow the this method to manipulate state
	 * and still allow the developer to make assumptions about the state.
	 * In most cases the implementation can be public.
	 */
	protected abstract double apply(double x);
}
