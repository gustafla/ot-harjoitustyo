package td.ui;

import td.domain.TowerDefense;
import td.domain.Map;
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
		Map map = new Map(20, 20, 0);
		towerDefense = new TowerDefense(map);
	}

	@Override
	public void start(Stage primaryStage) {
		// Set up stage and main BorderPane
		primaryStage.setTitle("Tower Defense");
		BorderPane root = new BorderPane();

		// Set up top UI labels
		HBox top = new HBox(16);
		moneyLabel = new Label();
		waveLabel = new Label();
		healthLabel = new Label();
		top.getChildren().addAll(moneyLabel, waveLabel, healthLabel);
		root.setTop(top);
		updateLabels();

		// Set up the map UI
		GridPane mapGridPane = new GridPane();
		for (int i = 0; i < towerDefense.getMap().getHeight(); i++) {
			for (int j = 0; j < towerDefense.getMap().getWidth(); j++) {
				switch(towerDefense.getMap().getTile(i, j)) {
					case WALL: mapGridPane.add(new Label("##"), j, i); break;
					case ROAD_UP: mapGridPane.add(new Label("^^"), j, i); break;
					case ROAD_DOWN: mapGridPane.add(new Label("vv"), j, i); break;
					case ROAD_LEFT: mapGridPane.add(new Label("<<"), j, i); break;
					case ROAD_RIGHT: mapGridPane.add(new Label(">>"), j, i); break;
				}
			}
		}
		root.setCenter(mapGridPane);

		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

    public static void main(String[] args) {
		launch(args);
    }
}
