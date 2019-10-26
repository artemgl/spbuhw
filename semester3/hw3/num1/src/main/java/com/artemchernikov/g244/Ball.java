package com.artemchernikov.g244;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Ball extends Pane {

    private final double width = 27;
    private final double height = 30;

    private final ImageView imageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("ball.png")));

    private double angle;
    private double power;

    private long existenceTime = 0;

    private Direction direction;

    public Ball(double power, double angle, double posX, double posY, Direction direction) {
        setWidth(width);
        setHeight(height);
        this.power = power;
        this.angle = angle;
        this.direction = direction;

        setTranslateX(posX);
        setTranslateY(posY);

        imageView.setViewport(new Rectangle2D(0, 0, 0, 0));
        getChildren().addAll(imageView);
    }

    public void move(long deltaTime) {
        setTranslateX(getTranslateX() +
                power * Math.cos(Math.toRadians(angle)) * deltaTime * (direction == Direction.RIGHT ? 1 : -1));
        setTranslateY(getTranslateY() +
                power * Math.sin(Math.toRadians(angle)) * (-deltaTime) +
                0.5 * deltaTime * (2 * existenceTime + deltaTime));
        existenceTime += deltaTime;
    }

}