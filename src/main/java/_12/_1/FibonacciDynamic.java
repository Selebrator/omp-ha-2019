package _12._1;

import provided._12._1.Fibonacci;
import java.util.HashMap;
import java.util.Map;

public class FibonacciDynamic extends Fibonacci {
    protected Map<Integer, Long> map = new HashMap<>();

    public FibonacciDynamic() {
        this.map.put(0, (long) 0);
        this.map.put(1, (long) 1);
    }

    @Override
    public long calculate(int n) {
        if (this.map.containsKey(n)) {
            return this.map.get(n);
        } else {
            long result = calculate(n-1) + calculate(n-2);
            this.map.put(n, result);
            return result;
        }
    }

}
