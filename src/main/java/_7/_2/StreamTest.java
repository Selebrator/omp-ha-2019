package _7._2;

import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        Stream<Integer> naturals = Stream.iterate(1, i -> i + 1);

        Stream<Integer> integers = Stream.iterate(0, i -> {
            int next;
            if (i>0) {
                next = i*(-1);
            } else {
                next = i*(-1)+1;
            }
            return next;
        });

        System.out.println(filterAndSum(naturals));
        System.out.println(filterAndSum(integers));
    }

    private static Integer filterAndSum(Stream<Integer> stream) {
        return stream.filter((i) -> i % 2 == 0)
                .limit(10)
                .reduce((a,b) -> a+b)
                .orElse(0);
    }
}
