package _10._4;

public class NameOutput extends Thread {
    private NumberData currentNumber;
    private int threadNumber;

    public NameOutput(int threadNumber, NumberData currentNumber) {
        this.threadNumber = threadNumber;
        this.currentNumber = currentNumber;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (currentNumber) {
                while (currentNumber.getNumber() != threadNumber) {
                    try {
                        currentNumber.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(this.getName());
                currentNumber.increaseNumber();
            }
        }
    }

    public static void main(String[] args) {
        int numberOfThreads = 3;
        NumberData currentNumber = new NumberData(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            Thread thread = new NameOutput(i, currentNumber);
            thread.start();
        }
    }
}

class NumberData {
    private int number = 0;
    private int modulo;

    public NumberData(int modulo) {
        this.modulo = modulo;
    }

    public int getNumber() {
            return number;
    }

    public void increaseNumber() {
        this.number = (this.number + 1) % this.modulo;
        notifyAll();
    }
}
