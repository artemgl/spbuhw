package com.artemchernikov.g244;

public class LagrangePolynomial {

    private double[] xValues;
    private double[] yValues;

    public LagrangePolynomial(double[] xValues, double[] yValues) {
        this.xValues = xValues;
        this.yValues = yValues;
    }

    public double get(double x) {
        double result = 0;
        int size = xValues.length;

        for (int i = 0; i < size; i++) {
            double basicsPol = 1;
            for (int j = 0; j < size; j++) {
                if (j != i) {
                    basicsPol *= (x - xValues[j])/(xValues[i] - xValues[j]);
                }
            }
            result += basicsPol * yValues[i];
        }

        return result;
    }

}
