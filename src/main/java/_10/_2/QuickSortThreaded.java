package _10._2;

import provided._10._2.*;

public class QuickSortThreaded extends QuickSort implements Runnable {
	private int[] numbers;
	private int leftIndex;
	private int rightIndex;

	public QuickSortThreaded() {
	}

	public QuickSortThreaded(int[] numbers, int leftIndex, int rightIndex) {
		this.numbers = numbers;
		this.leftIndex = leftIndex;
		this.rightIndex = rightIndex;
	}

	@Override
	public void run() {
		quickSort(numbers, leftIndex, rightIndex);
	}


	/**
	 * sortiert das uebergebene Array in aufsteigender Reihenfolge
	 * gemaess dem QuickSort-Algorithmus (parallel!)
	 */
	public static void sort(int[] numbers) {
		new QuickSortThreaded().quickSort(numbers, 0, numbers.length - 1);
	}

	/**
	 * der Quicksort-Algorithmus wird auf dem Array zwischen den
	 * angegebenen Indizes ausgefuehrt
	 */
	protected void quickSort(int[] numbers, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) {
			int pivotIndex = divide(numbers, leftIndex, rightIndex);
			Thread threadLeft = new Thread(new QuickSortThreaded(numbers, leftIndex, pivotIndex - 1));
			Thread threadRight = new Thread(new QuickSortThreaded(numbers, pivotIndex + 1, rightIndex));
			threadLeft.start();
			threadRight.start();
			try {
				threadLeft.join(); // Wait for first thread.
				threadRight.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
