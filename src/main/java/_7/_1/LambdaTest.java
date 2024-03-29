package _7._1;

public class LambdaTest {

    public static void main(String[] args) {
        Function<Double, Double> id = (x) -> x;

        Function<Double, Double> inverse = (x) -> -x;

        Function<Double, Double> timesTen = (x) -> 10*x;

        Function<Double, Double> divideByPi = (x) -> x/Math.PI;

        Function<Double, Long> round = Math::round;

        @SuppressWarnings("unchecked")
        Function<Double, Double> chain = makeChain(new Function[]{ inverse, id, timesTen, divideByPi });

        System.out.println(round.calculate(chain.calculate(5.5)));
    }

    private static Function<Double, Double> makeChain(final Function<Double, Double>[] functions) {
        return (x) -> {
            for (Function<Double, Double> f : functions) {
                x = f.calculate(x);
            }
            return x;
        };
    }

}
