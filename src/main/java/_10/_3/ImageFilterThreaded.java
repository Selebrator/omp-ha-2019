package _10._3;

import java.awt.*;
import provided._10._3.ImageFilter;

public class ImageFilterThreaded extends ImageFilter {

	class FilterThread implements Runnable {
		private int x1;
		private int x2;
		private int y1;
		private int y2;
		private Color[][] result;
		private float[][] filter;

		private FilterThread(int x1, int x2, int y1, int y2, Color[][] result, float[][] filter) {
			this.x1 = x1;
			this.x2 = x2;
			this.y1 = y1;
			this.y2 = y2;
			this.result = result;
			this.filter = filter;
		}

		@Override
		public void run() {
			for (int x = x1; x < x2; x++) {
				for (int y = y1; y < y2; y++) {
					filterPixel(result, x, y, filter);
				}
			}
		}
	}

	@Override
	protected Color[][] filterMatrix(float[][] filter) {
		Color[][] result = new Color[matrix.length][matrix[0].length];

		Thread threadLB = new Thread(new FilterThread(0, matrix.length/2, matrix[0].length/2, matrix[0].length,result,filter));
		Thread threadRB = new Thread(new FilterThread(matrix.length/2, matrix.length, matrix[0].length/2, matrix[0].length,result,filter));
		Thread threadLT = new Thread(new FilterThread(0, matrix.length/2, 0, matrix[0].length/2,result,filter));
		Thread threadRT = new Thread(new FilterThread(matrix.length/2, matrix.length, 0, matrix[0].length/2,result,filter));
		threadLB.start();
		threadRB.start();
		threadLT.start();
		threadRT.start();

		try {
			threadLB.join();
			threadRB.join();
			threadLT.join();
			threadRT.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return result;
	}

}
