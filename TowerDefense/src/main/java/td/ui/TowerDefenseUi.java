package td.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class TowerDefenseUi extends Application {

	@Override
	public void init() {
		// TODO initialize game logic
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Tower Defense");
		StackPane root = new StackPane();
		primaryStage.setScene(new Scene(root, 640, 480));
		primaryStage.show();
	}

    public static void main(String[] args) {
		launch(args);
    }
}
