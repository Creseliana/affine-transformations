package com.creseliana.util;

import javafx.geometry.Point2D;

public class MatrixMultiplierImpl implements MatrixMultiplier {

    @Override
    public double[][] multiply(double[][] firstMultiplier, double[][] secondMultiplier) {
        int firstRowSize = firstMultiplier.length;
        int firstColumnSize = firstMultiplier[0].length;
        int secondRowSize = secondMultiplier.length;
        int secondColumnSize = secondMultiplier[0].length;

        if (firstColumnSize != secondRowSize) {
            return null;
        }

        double[][] result = new double[firstRowSize][secondColumnSize];

        for (int i = 0; i < firstRowSize; i++) {
            for (int j = 0; j < secondColumnSize; j++) {
                for (int k = 0; k < secondRowSize; k++) {

                    result[i][j] += firstMultiplier[i][k] * secondMultiplier[k][j];
                }
            }
        }
        return result;
    }

    public double[][] convertPointToMatrix(Point2D point) {
        double[][] matrix = new double[3][1];
        matrix[0][0] = point.getX();
        matrix[1][0] = point.getY();
        matrix[2][0] = 1;
        return matrix;
    }

    public Point2D convertMatrixToPoint(double[][] matrix) {
        if (matrix.length != 3 && matrix[0].length != 1) {
            throw new RuntimeException();
        }
        return new Point2D((int) matrix[0][0], (int) matrix[1][0]);
    }

    public double[][] getCoordinatesMatrix(double x, double y) {
        double[][] coordinatesMatrix = new double[3][3];
        coordinatesMatrix[0][0] = 1;
        coordinatesMatrix[1][1] = 1;
        coordinatesMatrix[2][2] = 1;
        coordinatesMatrix[2][0] = x;
        coordinatesMatrix[2][1] = y;
        return coordinatesMatrix;
    }
}
