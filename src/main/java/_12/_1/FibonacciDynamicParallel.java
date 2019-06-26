package _12._1;


public class FibonacciDynamicParallel extends FibonacciDynamic {

    @Override
    public long calculate(int n) {
        if (this.map.containsKey(n)) {
            return this.map.get(n);
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

            long result = thread1.getResult() + thread2.getResult();
            this.map.put(n, result);
            return result;
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
