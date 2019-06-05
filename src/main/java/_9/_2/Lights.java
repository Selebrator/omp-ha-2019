package _9._2;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Optional;

public class Lights extends Application {

	private static final int DEFAULT_SIZE = 4;
	private static final int GAP = 2;
	private static final Paint ON = Color.YELLOW;
	private static final Paint OFF = Color.WHITE;

	@Override
	public void start(Stage primaryStage) throws Exception {
		final int size = Optional.ofNullable(this.getParameters().getNamed().get("size"))
				.map(Integer::parseInt).orElse(DEFAULT_SIZE);
		GridPane grid = new GridPane();
		Scene scene = new Scene(grid, 600, 600);
		scene.setFill(Color.BLACK);
		grid.setHgap(GAP);
		grid.setVgap(GAP);
		for(int x = 0; x < size; x++) {
			for(int y = 0; y < size; y++) {
				Rectangle rec = new Rectangle(150, 150);
				rec.heightProperty().bind(scene.heightProperty().subtract(size * GAP).divide(size));
				rec.widthProperty().bind(scene.widthProperty().subtract(size * GAP).divide(size));
				rec.setFill(OFF);
				grid.add(rec, x, y);
			}
		}
		for(int x = 0; x < size; x++) {
			for(int y = 0; y < size; y++) {
				Rectangle rec = getNodeByRowColumnIndex(x, y, grid);
				assert rec != null;
				Rectangle up = getNodeByRowColumnIndex(x, y - 1, grid);
				Rectangle down = getNodeByRowColumnIndex(x, y + 1, grid);
				Rectangle left = getNodeByRowColumnIndex(x - 1, y, grid);
				Rectangle right = getNodeByRowColumnIndex(x + 1, y, grid);
				rec.setOnMouseClicked(event -> {
					toggle(rec);
					toggle(up);
					toggle(down);
					toggle(left);
					toggle(right);
				});
			}
		}

		primaryStage.setScene(scene);
		primaryStage.setTitle("Lights");
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

	public static <T extends Node> T getNodeByRowColumnIndex(final int column, final int row, GridPane gridPane) {
		ObservableList<Node> children = gridPane.getChildren();
		for(Node node : children) {
			if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
				return (T) node;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
