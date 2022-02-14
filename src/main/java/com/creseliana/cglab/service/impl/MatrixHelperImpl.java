package com.creseliana.cglab.service.impl;

import com.creseliana.cglab.service.MatrixHelper;
import javafx.geometry.Point2D;

public class MatrixHelperImpl implements MatrixHelper {

    @Override
    public double[][] convertPointToMatrix(Point2D point) {
        double[][] matrix = new double[3][1];
        matrix[0][0] = point.getX();
        matrix[1][0] = point.getY();
        matrix[2][0] = 1;
        return matrix;
    }

    @Override
    public Point2D convertMatrixToPoint(double[][] matrix) {
        if (matrix.length != 3 && matrix[0].length != 1) {
            throw new RuntimeException();
        }
        return new Point2D(Math.round(matrix[0][0]), Math.round(matrix[1][0]));
    }
}
