package _10._1;

public class SearchThread extends Thread {
    private int[] array;
    private int value;
    private int from;
    private int to;
    private boolean found = false;

    public SearchThread(int[] array, int value, int from, int to) {
        this.array = array;
        this.value = value;
        this.from = from;
        this.to = to;
    }

    public void run() {
        for (int i = this.from; i < this.to; i++) {
            if (this.array[i] == this.value) {
                this.found = true;
                System.out.println(this.getName() + ": found");
                return;
            }
        }
        System.out.println(this.getName() + ": not found");
    }

    public boolean getFound() {
        return found;
    }


    public static void main(String[] args) {
        int[] array = { 2, 7, 3, 9, 23 };
        arraySearch(array,7);
    }

    private static void arraySearch(int[] array, int value) {
        SearchThread thread1 = new SearchThread(array, value, 0, array.length/2);
        SearchThread thread2 = new SearchThread(array, value, array.length/2, array.length);
        thread1.setName("Thread 1");
        thread2.setName("Thread 2");
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
            if (thread1.getFound() || thread2.getFound()) {
                System.out.println("Found: true");
            } else {
                System.out.println("Found: false");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
