package td.ui;

import td.domain.TowerDefense;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class TowerDefenseUi extends Application {
	
	private TowerDefense towerDefense;

	private Label moneyLabel;
	private Label waveLabel;
	private Label healthLabel;

	private void updateLabels() {
		moneyLabel.setText("Money: " + towerDefense.getMoney());
		waveLabel.setText("Wave: " + towerDefense.getWave());
		healthLabel.setText("Health: " + towerDefense.getHealth());
	}

	@Override
	public void init() {
		towerDefense = new TowerDefense();
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Tower Defense");
		BorderPane root = new BorderPane();
		HBox top = new HBox(16);

		moneyLabel = new Label();
		waveLabel = new Label();
		healthLabel = new Label();
		top.getChildren().addAll(moneyLabel, waveLabel, healthLabel);
		root.setTop(top);

		updateLabels();

		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

    public static void main(String[] args) {
		launch(args);
    }
}
