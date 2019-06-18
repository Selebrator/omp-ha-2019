package _11._1;

import provided.IO;
import java.util.concurrent.Semaphore;

class Output extends Thread {

    public void run() {
        try {
            InOut.getSemaphore().acquire();
            System.out.println(InOut.getValue() * InOut.getValue());
        } catch (InterruptedException e) {

        }
    }
}

class Input extends Thread {

    public void run() {
        InOut.setValue(IO.readInt("Value: "));
        InOut.getSemaphore().release();
    }
}

public class InOut {

    private static Semaphore semaphore = new Semaphore(0);
    private static int value = 0;

    public static Semaphore getSemaphore() { return semaphore; }

    public static int getValue() {
        return value;
    }

    public static void setValue(int value) {
        InOut.value = value;
    }

    public static void main(String[] args) {
        new Output().start();
        new Input().start();
    }

}
