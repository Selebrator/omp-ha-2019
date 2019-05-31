package provided._8;

import _8.Lecture;
import java.io.IOException;

public class LectureOMP {

	public static void main(String[] args) {
		try {
			// task 1
			Lecture omp = Lecture.load("omp.dat");
			System.out.println(omp);
			// task 2
			//Lecture.saveText("omp.txt", omp);
			//omp = Lecture.loadText("omp.txt");
			//System.out.println(omp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
