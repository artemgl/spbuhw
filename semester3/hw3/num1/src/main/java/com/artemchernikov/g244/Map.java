package com.artemchernikov.g244;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.Random;

/**A class describing map*/
public class Map extends Canvas {

    private LagrangePolynomial landscape;
    private int pointsCount;

    public Map(double width, double height, int pointsCount, double seed) {
        super(width, height);
        this.pointsCount = pointsCount;
        double[] xValues = new double[pointsCount];
        double[] yValues = new double[pointsCount];
        Random random = new Random();

        for (int i = 0; i < pointsCount; i++) {
            xValues[i] = i;
            yValues[i] = seed * random.nextDouble() * (random.nextBoolean() ? 1 : -1);
        }

        landscape = new LagrangePolynomial(xValues, yValues);
    }

    /**
     * A method checks whether the point is coordinate of land
     * @param x abscissa of the point
     * @param y ordinate of the point
     * @return true if the received point looks at the land and false otherwise
     * */
    public boolean isLand(double x, double y) {
        double coordX = x / getWidth() * (pointsCount - 1);
        double coordY = 1 - y / getHeight() * 2;
        return landscape.get(coordX) > coordY;
    }

    /**A method renders the map*/
    public void render() {
        GraphicsContext graphicsContext = getGraphicsContext2D();

        Paint fill = graphicsContext.getFill();
        graphicsContext.setFill(Color.rgb(110, 210, 245));
        graphicsContext.fillRect(0, 0, getWidth(), getHeight());

        Paint stroke = graphicsContext.getStroke();
        graphicsContext.setStroke(Color.rgb(245, 230, 145));
        graphicsContext.setFill(Color.rgb(245, 230, 145));

        double beginX = 0;
        double beginY = (1 - landscape.get(0)) * getHeight() / 2;

        double vectorX = getWidth() / 100;
        double vectorY = 0;

        while (beginX < getWidth()) {
            vectorY = (-landscape.get((beginX + vectorX) / getWidth() * (pointsCount - 1)) + 1) * getHeight() / 2 - beginY;

            graphicsContext.fillRect(beginX, Math.max(beginY, beginY + vectorY), vectorX,
                    getHeight() - Math.max(beginY, beginY + vectorY));
            graphicsContext.fillPolygon(new double[] {beginX, beginX + (vectorY < 0 ? vectorX : 0), beginX + vectorX},
                    new double[] {beginY, beginY + vectorY, beginY + (vectorY < 0 ? 0 : vectorY)}, 3);
            graphicsContext.strokeLine(beginX, Math.max(beginY, beginY + vectorY), beginX, getHeight());

            beginX += vectorX;
            beginY += vectorY;
        }

        graphicsContext.setFill(fill);
        graphicsContext.setStroke(stroke);
    }

}
