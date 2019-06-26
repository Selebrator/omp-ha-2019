package _12._1;

import provided._12._1.Fibonacci;

public class FibonacciParallel extends Fibonacci {

    @Override
    public long calculate(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            FibonacciThread thread1 = new FibonacciThread(n-1);
            FibonacciThread thread2 = new FibonacciThread(n-2);
            thread1.start();
            thread2.start();

            try {
                thread1.join();
                thread2.join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return thread1.getResult() + thread2.getResult();
        }
    }


    class FibonacciThread extends Thread {
        private int n;
        private long result;

        public FibonacciThread(int n) {
            this.n = n;
        }

        @Override
        public void run() {
            result = calculate(n);
        }

        public long getResult() {
            return result;
        }
    }
}


