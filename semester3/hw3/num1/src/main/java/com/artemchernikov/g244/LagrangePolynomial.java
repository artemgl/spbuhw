package com.artemchernikov.g244;

/**A class describing polynomial given by some values at some points*/
public class LagrangePolynomial {

    private double[] xValues;
    private double[] yValues;

    public LagrangePolynomial(double[] xValues, double[] yValues) {
        this.xValues = xValues;
        this.yValues = yValues;
    }

    /**
     * A method calculates value of the polynomial at the point
     * @param x point to calculate value at
     * @return value at the received point
     * */
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
