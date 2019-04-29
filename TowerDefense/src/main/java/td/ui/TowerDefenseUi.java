package td.ui;

import java.util.AbstractMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.IOException;
import td.domain.TowerDefense;
import td.domain.Field;
import td.domain.Tile;
import td.domain.Enemy;
import td.domain.Tower;
import td.domain.Shot;
import td.dao.TowerDefenseFileDao;
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
    private final double SHOT_FADE_TIME = 1.;

    private TowerDefense towerDefense;
    private TowerDefenseFileDao dao;

    private List<Shot> shots;

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
        for (Tower t: towerDefense.getTowers()) {
            gc.fillPolygon(new double[]{t.getPositionX()-5, t.getPositionX(), t.getPositionX()+5},
                    new double[]{t.getPositionY()+5, t.getPositionY()-5, t.getPositionY()+5}, 3);
        }

        // Draw tower mouseOver
        if (placingTower != null) {
            if (towerDefense.getField().getTileByPosition(canvasMouseY, canvasMouseX) == Tile.WALL) {
                gc.setFill(Color.rgb(0, 255, 0, 0.4));
            } else {
                gc.setFill(Color.rgb(255, 0, 0, 0.4));
            }
            gc.fillOval(
                    canvasMouseX - placingTower.getRange(),
                    canvasMouseY - placingTower.getRange(),
                    placingTower.getRange() * 2,
                    placingTower.getRange() * 2);
        }
    }

    private void drawEnemies() {
        for (Enemy enemy: towerDefense.getEnemies()) {
            gc.setFill(Color.rgb(
                        255,
                        (int) ((1. - (enemy.getHealth() / enemy.getMaxHealth())) * 255),
                        0));
            gc.fillOval(
                    enemy.getPositionX() - 5,
                    enemy.getPositionY() - 5,
                    10,
                    10);
        }
    }

    private void drawShots() {
        gc.setLineWidth(3);
        for (Shot shot: shots) {
            double timeLeft = (SHOT_FADE_TIME - shot.getTime()) / SHOT_FADE_TIME;
            gc.setStroke(Color.rgb(255, 255, 0, timeLeft));
            gc.strokeLine(shot.getX1(), shot.getY1(), shot.getX2(), shot.getY2());
        }
    }

    @Override
    public void init() {
        shots = new ArrayList<>();

        dao = new TowerDefenseFileDao("./save.ser");
        try {
            towerDefense = dao.load();
        } catch (IOException e) {
            Field field = new Field(
                    CANVAS_HEIGHT / TILE_SIZE,
                    CANVAS_WIDHT / TILE_SIZE,
                    TILE_SIZE,
                    0);
            towerDefense = new TowerDefense(field);
        }
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

                shots.addAll(towerDefense.update(deltaTime));

                Iterator<Shot> it = shots.iterator();
                while (it.hasNext()) {
                    Shot shot = it.next();
                    shot.updateTime(deltaTime);
                    if (shot.getTime() >= SHOT_FADE_TIME) {
                        it.remove();
                    }
                }

                updateLabels();

                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                drawField();
                drawEnemies();
                drawShots();

                if (towerDefense.isWaveOver()) {
                    try {
                        dao.save(towerDefense);
                    } catch (Exception e) {
                        System.out.println("Failed to save game state");
                        e.printStackTrace();
                    }

                    waveButton.setText("Next wave");
                    waveButton.setVisible(true);

                    stop();
                }

                if (towerDefense.isGameOver()) {
                    root.setTop(null);
                    root.setCenter(new Label("Game over!"));
                    dao.delete();
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
        updateTowerButtonText();
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
            if (towerDefense.isWaveOver()) {
                updateLabels();
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                drawField();
            }
        });

        // Set up canvas placingTower mousePressed
        canvas.setOnMousePressed((MouseEvent event) -> {
            if (placingTower != null) {
                placingTower.setPositionY(event.getY());
                placingTower.setPositionX(event.getX());
                if (towerDefense.addTower(placingTower)) {
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
