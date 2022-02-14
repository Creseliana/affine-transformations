package com.creseliana.cglab.service;

import javafx.geometry.Point2D;

public interface MatrixHelper {

    double[][] convertPointToMatrix(Point2D point);

    Point2D convertMatrixToPoint(double[][] matrix);
}
