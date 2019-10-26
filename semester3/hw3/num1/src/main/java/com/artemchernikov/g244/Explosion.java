package com.artemchernikov.g244;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Explosion extends Pane {

    private final double width = 64;
    private final double height = 64;

    private final ImageView imageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("explosions.png")));

    private long existenceTime = 0;
    private final long maxExistenceTime = 16;

    private double offsetX = 64;
    private double textureX = 0;

    public Explosion(double posX, double posY) {
        setWidth(width);
        setHeight(height);
        setTranslateX(posX);
        setTranslateY(posY);

        imageView.setViewport(new Rectangle2D(0, 0, 0, 0));
        getChildren().addAll(imageView);
    }

    public void increaseExistenceTime(long deltaTime) {
        existenceTime += deltaTime;
        imageView.setViewport(new Rectangle2D(textureX += offsetX, 0, 64, 64));
        if (existenceTime == maxExistenceTime) {
            getChildren().removeAll(imageView);
        }
    }

}
