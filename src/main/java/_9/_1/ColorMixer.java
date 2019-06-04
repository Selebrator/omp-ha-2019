package _9._1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ColorMixer extends Application {

	@Override
	public void start(Stage stage) {
		VBox vBox = new VBox(10);
		vBox.setAlignment(Pos.TOP_CENTER);
		vBox.setPadding(new Insets(10));

		Rectangle rectangle = new Rectangle(200, 200);

		Slider red = colorSlider(Color.RED);
		Slider green = colorSlider(Color.GREEN);
		Slider blue = colorSlider(Color.BLUE);
		red.valueProperty().addListener((observable, oldValue, newValue) -> rectangle.setFill(Color.rgb(newValue.intValue(), (int) green.getValue(), (int) blue.getValue())));
		green.valueProperty().addListener((observable, oldValue, newValue) -> rectangle.setFill(Color.rgb((int) red.getValue(), newValue.intValue(), (int) blue.getValue())));
		blue.valueProperty().addListener((observable, oldValue, newValue) -> rectangle.setFill(Color.rgb((int) red.getValue(), (int) green.getValue(), newValue.intValue())));

		vBox.getChildren().addAll(rectangle, red, green, blue);
		Scene scene = new Scene(new StackPane(vBox), 600, 360);
		stage.setScene(scene);
		stage.setTitle("Color-Mixer");
		stage.setResizable(false);
		stage.show();
	}

	private Slider colorSlider(Paint fill) {
		Slider slider = new Slider(0, 255, 0);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setBackground(new Background(new BackgroundFill(fill, CornerRadii.EMPTY, Insets.EMPTY)));
		return slider;
	}

	public static void main(String[] args) {
		launch();
	}
}