package _12._1;

import provided._12._1.Fibonacci;

public class FibonacciRecursive extends Fibonacci {

    @Override
    public long calculate(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return calculate(n-1) + calculate(n-2);
        }
    }
}
