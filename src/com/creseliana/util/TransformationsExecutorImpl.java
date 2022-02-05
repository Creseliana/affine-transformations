package com.creseliana.util;

import javafx.scene.shape.Polygon;

import java.util.Arrays;

public class TransformationsExecutorImpl implements TransformationsExecutor {

    public Polygon move(Polygon polygon, double x, double y) {
        double[][] matrix = getIdentityMatrix();
        matrix[0][2] = x;
        matrix[1][2] = y;


    }

    private double[][] getIdentityMatrix() {
        return new double[][] {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
    }
}
