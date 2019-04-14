package td.ui;

import td.domain.TowerDefense;
import td.domain.Map;
import td.domain.Tile;
import td.domain.Enemy;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.animation.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;

public class TowerDefenseUi extends Application {

	private final double NS_IN_SEC = 1000000000.0;
	private final int CANVAS_WIDHT = 800;
	private final int CANVAS_HEIGHT = 600;
	private final int TILE_SIZE = 20;
	
	private TowerDefense towerDefense;

	private Label moneyLabel;
	private Label waveLabel;
	private Label healthLabel;

	private Canvas canvas;
	private GraphicsContext gc;

	private void updateLabels() {
		moneyLabel.setText("Money: " + towerDefense.getMoney());
		waveLabel.setText("Wave: " + towerDefense.getWaveNumber());
		healthLabel.setText("Health: " + towerDefense.getHealth());
	}

	private void drawMap() {
		Map m = towerDefense.getMap();
		for (int i = 0; i < m.getHeight(); i++) {
			for (int j = 0; j < m.getWidth(); j++) {
				if (i == (int) (m.getSpawnPositionY() / TILE_SIZE)
						&& j == (int) (m.getSpawnPositionX() / TILE_SIZE)) {
					gc.setFill(Color.PINK);
				} else if (i == m.getBaseTileY() && j == m.getBaseTileX()) {
					gc.setFill(Color.LIGHTGREEN);
				} else {
					gc.setFill(Color.LIGHTGRAY);
				}
				if (m.getTile(i, j) != Tile.WALL) {
					gc.fillRect(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
				}
			}
		}
	}

	private void drawEnemies() {
		gc.setFill(Color.RED);
		for (Enemy enemy: towerDefense.getEnemies()) {
			gc.fillOval(
					enemy.getPositionX() - 5,
					enemy.getPositionY() - 5,
					10,
					10);
		}
	}

	@Override
	public void init() {
		Map map = new Map(
				CANVAS_HEIGHT / TILE_SIZE,
				CANVAS_WIDHT / TILE_SIZE,
				TILE_SIZE,
				0);
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

		// Set up the canvas
		canvas = new Canvas(CANVAS_WIDHT, CANVAS_HEIGHT);
		gc = canvas.getGraphicsContext2D();
		root.setCenter(canvas);

		// Set up the combined graphics and game logic loop
		new AnimationTimer() {
			long frames = 0;
			final long FPS_INTERVAL_NS = (long)(2 * NS_IN_SEC);
			long lastFramecountTimeNs = System.nanoTime();
			long lastTimeNs = System.nanoTime();

			public void handle(long currentTimeNs) {
				double deltaTime = (currentTimeNs - lastTimeNs) / NS_IN_SEC;
				lastTimeNs = currentTimeNs;

				if (currentTimeNs - lastFramecountTimeNs > FPS_INTERVAL_NS) {
					System.out.println("Average framerate "
							+ frames / (FPS_INTERVAL_NS / NS_IN_SEC));
					frames = 0;
					lastFramecountTimeNs = currentTimeNs;
				}

				towerDefense.update(deltaTime);

				updateLabels();

				gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				drawMap();
				drawEnemies();

				frames++;
			}
		}.start();

		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
