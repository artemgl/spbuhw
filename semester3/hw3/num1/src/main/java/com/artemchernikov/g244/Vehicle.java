package com.artemchernikov.g244;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

/**A method describes the vehicle*/
public class Vehicle extends Pane {

    private final double width = 43;
    private final double height = 53;

    private Pane cannon;

    private final ImageView baseImageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("base.png")));
    private final ImageView cannonImageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("cannon.png")));

    private Direction direction = Direction.LEFT;
    private final double DEFAULT_ROTATION = 20;

    private ArrayList<Ball> balls = new ArrayList<>();

    public Vehicle(double posX, double posY, double rotation, double scale, Direction direction) {
        setWidth(width);
        setHeight(height);
        setScaleX(scale);
        setScaleY(scale);
        setTranslateX(posX);
        setTranslateY(posY);
        setRotate(rotation);
        setDirection(direction);

        cannon = new Pane();
        cannonImageView.setViewport(new Rectangle2D(0, 0, 0, 0));
        cannon.getChildren().addAll(cannonImageView);
        cannon.setTranslateX(-6);
        cannon.setTranslateY(12);

        baseImageView.setViewport(new Rectangle2D(0, 0, 0, 0));
        getChildren().addAll(baseImageView, cannon);
    }

    /**
     * A method moves the vehicle
     * @param dx horizontal offset of moving
     * @param dy vertical offset of moving
     * */
    public void increasePosition(double dx, double dy) {
        if (dx != 0) {
            setDirection(dx > 0 ? Direction.RIGHT : Direction.LEFT);
        }
        setTranslateX(getTranslateX() + dx);
        setTranslateY(getTranslateY() + dy);
    }

    /**
     * A method changes rotation of the cannon
     * @param dr angle to change the rotation on
     * */
    public void increaseRotation(double dr) {
        double rotation = cannon.getRotate() + DEFAULT_ROTATION;
        rotation += dr;
        if (rotation > 90) {
            rotation = 90;
        } else if (rotation < 0) {
            rotation = 0;
        }
        cannon.setRotate(rotation - DEFAULT_ROTATION);
    }

    private void setDirection(Direction direction) {
        if (this.direction != direction) {
            setScaleX(-getScaleX());
        }
        this.direction = direction;
    }

    private double getRotation() {
        return cannon.getRotate() + DEFAULT_ROTATION;
    }

    /**
     * A method makes the vehicle shoot with the balls
     * @return flying ball the vehicle has shot with
     * */
    public Ball shoot() {
        Ball newBall = new Ball(30, getRotation(), getTranslateX(), getTranslateY(), direction);
        balls.add(newBall);
        return newBall;
    }

    /**
     * A method gives collection the balls
     * @return list of all the balls which the vehicle fired
     * */
    public ArrayList<Ball> getBalls() {
        return balls;
    }

}
