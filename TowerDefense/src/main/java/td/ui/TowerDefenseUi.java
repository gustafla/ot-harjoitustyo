package td.ui;

import java.util.AbstractMap;
import td.domain.TowerDefense;
import td.domain.Field;
import td.domain.Tile;
import td.domain.Enemy;
import td.domain.Tower;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.animation.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;

public class TowerDefenseUi extends Application {

	private final double NS_IN_SEC = 1000000000.0;
	private final int CANVAS_WIDHT = 800;
	private final int CANVAS_HEIGHT = 600;
	private final int TILE_SIZE = 20;
	
	private TowerDefense towerDefense;

	private Label moneyLabel;
	private Label waveLabel;
	private Label healthLabel;
	private Button waveButton;
	private Button towerButton;
	private Tower placingTower;

	private double canvasMouseY;
	private double canvasMouseX;

	private Canvas canvas;
	private GraphicsContext gc;

	private void updateLabels() {
		moneyLabel.setText("Money: " + towerDefense.getMoney());
		waveLabel.setText("Wave: " + towerDefense.getWaveNumber());
		healthLabel.setText("Health: " + towerDefense.getHealth());
	}

	private void updateTowerButtonText() {
		if (placingTower == null) {
			towerButton.setText("Buy tower");
		} else {
			towerButton.setText("Cancel buying");
		}
	}

	private void drawField() {
		Field m = towerDefense.getField();

		// Draw roads, spawn and base
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

		// Draw towers
		gc.setFill(Color.BLUE);
		for (AbstractMap.SimpleEntry<Integer, Integer> coordinates: m.getTowerCoordinates()) {
			int y = coordinates.getKey() * TILE_SIZE + TILE_SIZE / 2;
			int x = coordinates.getValue() * TILE_SIZE + TILE_SIZE / 2;
			gc.fillPolygon(new double[]{x-5, x, x+5}, new double[]{y+5, y-5, y+5}, 3);
		}

		// Draw tower mouseOver
		if (placingTower != null) {
			if (towerDefense.getField().isPositionFree(canvasMouseY, canvasMouseX)) {
				gc.setFill(Color.rgb(0, 255, 0, 0.4));
			} else {
				gc.setFill(Color.rgb(255, 0, 0, 0.4));
			}
			gc.fillRect(
					(int) (canvasMouseX / TILE_SIZE) * TILE_SIZE,
					(int) (canvasMouseY / TILE_SIZE) * TILE_SIZE,
					TILE_SIZE, TILE_SIZE);
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
		Field field = new Field(
				CANVAS_HEIGHT / TILE_SIZE,
				CANVAS_WIDHT / TILE_SIZE,
				TILE_SIZE,
				0);
		towerDefense = new TowerDefense(field);
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
		AnimationTimer at = new AnimationTimer() {
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
				drawField();
				drawEnemies();

				if (towerDefense.isWaveOver()) {
					waveButton.setText("Next wave");
					waveButton.setVisible(true);
					stop();
				}

				frames++;
			}
		};

		// Set up next wave button
		waveButton = new Button("Start wave");
		top.getChildren().add(waveButton);
		waveButton.setOnAction((ActionEvent event) -> {
			if (towerDefense.isWaveOver()) {
				towerDefense.nextWave();
				at.start();
				waveButton.setVisible(false);
			}
		});

		// Set up tower shop
		placingTower = null;
		towerButton = new Button();
		top.getChildren().add(towerButton);
		towerButton.setOnAction((ActionEvent event) -> {
			if (placingTower == null) {
				placingTower = towerDefense.buyTower();
			} else {
				towerDefense.refundTower();
				placingTower = null;
			}
			updateTowerButtonText();
		});

		// Set up canvas placingTower mouseMoved
		canvas.setOnMouseMoved((MouseEvent event) -> {
			canvasMouseY = event.getY();
			canvasMouseX = event.getX();
		});

		// Set up canvas placingTower mousePressed
		canvas.setOnMousePressed((MouseEvent event) -> {
			if (placingTower != null) {
				if (towerDefense.getField().addTowerByPosition(
							event.getY(),
							event.getX(),
							placingTower)) {
					placingTower = null;
					updateTowerButtonText();
				}
			}
		});

		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
