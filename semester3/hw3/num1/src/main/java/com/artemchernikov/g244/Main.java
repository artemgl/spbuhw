package com.artemchernikov.g244;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Main extends Application {

    public static final double WIDTH = 1280;
    public static final double HEIGHT = 720;

    private HashMap<KeyCode, Boolean> keys = new HashMap<>();

    private Vehicle tank = new Vehicle(WIDTH / 2, HEIGHT, 0, 1.15, Direction.RIGHT);
    private Map map = new Map(WIDTH, HEIGHT, 9, 0.5);

    private ArrayList<Explosion> explosions = new ArrayList<>();

    private Pane gameRoot = new Pane();

    @Override
    public void start(Stage primaryStage) throws Exception {
        map.render();

        moveTankToSurface();

        gameRoot.setPrefSize(WIDTH, HEIGHT);
        gameRoot.getChildren().addAll(map, tank);

        Scene scene = new Scene(gameRoot);
        scene.setOnKeyPressed(event->keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event->keys.put(event.getCode(), false));

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();

        primaryStage.setTitle("Cannon");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }

    private void moveTankToSurface() {
        if (map.isLand(tank.getTranslateX() + tank.getWidth() / 2, tank.getTranslateY() + tank.getHeight())) {
            do {
                tank.increasePosition(0, -0.01);
            } while (map.isLand(tank.getTranslateX() + tank.getWidth() / 2, tank.getTranslateY() + tank.getHeight()));
        } else {
            do {
                tank.increasePosition(0, 0.01);
            } while (!map.isLand(tank.getTranslateX() + tank.getWidth() / 2, tank.getTranslateY() + tank.getHeight()));
        }
    }

    private void update() {

        if (isPressed(KeyCode.RIGHT)) {
            if (tank.getTranslateX() < WIDTH - 2 * tank.getWidth()) {
                tank.increasePosition(3, 0);
                moveTankToSurface();
            }
        }
        if (isPressed(KeyCode.LEFT)) {
            if (tank.getTranslateX() > tank.getWidth()) {
                tank.increasePosition(-3, 0);
                moveTankToSurface();
            }
        }
        if (isPressed(KeyCode.UP)) {
            tank.increaseRotation(2);
        }
        if (isPressed(KeyCode.DOWN)) {
            tank.increaseRotation(-2);
        }
        if (isPressed(KeyCode.ENTER)) {
            keys.put(KeyCode.ENTER, false);
            gameRoot.getChildren().addAll(tank.shoot());
        }

        moveBalls();

        for (Explosion explosion : explosions) {
            explosion.increaseExistenceTime(1);
        }
    }

    private void moveBalls() {
        for (Iterator<Ball> iterator = tank.getBalls().iterator(); iterator.hasNext(); ) {
            Ball ball = iterator.next();
            ball.move(1);

            if (ball.getTranslateX() + ball.getHeight() < 0 || ball.getTranslateX() > WIDTH || ball.getTranslateY() > HEIGHT) {
                iterator.remove();
            }
            if (map.isLand(ball.getTranslateX() + ball.getWidth() / 2, ball.getTranslateY() + ball.getHeight() / 2)) {
                gameRoot.getChildren().removeAll(ball);
                if (tank.getBalls().contains(ball)) {
                    iterator.remove();
                }

                Explosion explosion = new Explosion(ball.getTranslateX(), ball.getTranslateY());
                explosions.add(explosion);
                gameRoot.getChildren().add(explosion);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
