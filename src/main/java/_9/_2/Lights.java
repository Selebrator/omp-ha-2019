package _9._2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Optional;

public class Lights extends Application {

	private static final int DEFAULT_SIZE = 4;
	private static final Paint ON = Color.YELLOW;
	private static final Paint OFF = Color.WHITE;

	@Override
	public void start(Stage primaryStage) {
		final int size = Optional.ofNullable(this.getParameters().getNamed().get("size"))
				.map(Integer::parseInt).orElse(DEFAULT_SIZE);
		Rectangle[][] positions = new Rectangle[size][size];
		GridPane grid = new GridPane();
		Scene scene = new Scene(grid, 600, 600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Lights");

		for(int row = 0; row < size; row++) {
			for(int column = 0; column < size; column++) {
				Rectangle rec = new Rectangle();
				rec.setFill(OFF);
				rec.setStroke(Color.BLACK);
				rec.heightProperty().bind(scene.heightProperty().divide(size).subtract(1)); // -1 for stroke
				rec.widthProperty().bind(scene.widthProperty().divide(size).subtract(1));
				{
					final int x = row, y = column;
					rec.setOnMouseClicked(event -> {
						toggle(rec);
						toggle(getMatrixIndexSafe(positions, x, y - 1));
						toggle(getMatrixIndexSafe(positions, x, y + 1));
						toggle(getMatrixIndexSafe(positions, x - 1, y));
						toggle(getMatrixIndexSafe(positions, x + 1, y));
					});
				}
				grid.add(rec, row, column);
				positions[row][column] = rec;
			}
		}
		primaryStage.show();
	}

	private void toggle(Rectangle rec) {
		if(rec == null) {
			return;
		}
		Paint color = rec.getFill();
		if(color == ON) {
			rec.setFill(OFF);
		} else if(color == OFF) {
			rec.setFill(ON);
		} else {
			throw new IllegalStateException();
		}
	}

	private static <T> T getMatrixIndexSafe(final T[][] positions, final int row, final int column) {
		return (0 <= row && row < positions.length) && (0 <= column && column < positions[row].length)
				? positions[row][column]
				: null;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
