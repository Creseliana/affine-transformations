package com.creseliana.cglab.service.impl;

import com.creseliana.cglab.model.TransformPolygon;
import com.creseliana.cglab.service.MatrixHelper;
import com.creseliana.cglab.service.MatrixMultiplier;
import com.creseliana.cglab.service.TransformationsExecutor;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TransformationsExecutorImpl implements TransformationsExecutor {

    private final MatrixMultiplier matrixMultiplier;
    private final MatrixHelper matrixHelper;
    private final double halfScreenX;
    private final double halfScreenY;
    private final double polygonScale;

    public void translate(TransformPolygon polygon, double x, double y) {
        double[][] matrix = polygon.getMatrix();
        matrix[0][2] = matrix[0][2] + x;
        matrix[1][2] = matrix[1][2] + y;

        executeTransformation(polygon);
    }

    public void dilate(TransformPolygon polygon, double index) {
        double[][] matrix = polygon.getMatrix();
        matrix[0][0] = matrix[0][0] * index;
        matrix[1][1] = matrix[1][1] * index;

        executeTransformation(polygon);
    }

    public void rotate(TransformPolygon polygon, double degree) {
        polygon.setDegree(polygon.getDegree() + degree);
        double[][] matrix = polygon.getMatrix();
        double cos = Math.cos(Math.toRadians(polygon.getDegree()));
        double sin = Math.sin(Math.toRadians(polygon.getDegree()));
        matrix[0][0] = cos;
        matrix[1][1] = cos;
        matrix[0][1] = sin;
        matrix[1][0] = sin * -1;

        executeTransformation(polygon);
    }

    public void reflect(TransformPolygon polygon, boolean x, boolean y) {
        double[][] matrix = polygon.getMatrix();
        matrix[0][0] = matrix[0][0] * (x ? -1 : 1);
        matrix[1][1] = matrix[1][1] * (y ? -1 : 1);

        executeTransformation(polygon);
    }

    public void reset(TransformPolygon polygon, List<Point2D> pointList) {
        if (polygon.getPolygon() == null) {
            Polygon innerPolygon = new Polygon();
            innerPolygon.setStroke(Color.BLACK);
            innerPolygon.setFill(Color.TRANSPARENT);
            polygon.setPolygon(innerPolygon);
        }
        polygon.setPointList(pointList);
        polygon.getPolygon().getPoints().setAll(getUngroupedPointList(pointList));
        polygon.setMatrix(getIdentityMatrix());

        executeTransformation(polygon);
    }

    private double[][] getIdentityMatrix() {
        return new double[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
    }

    private void executeTransformation(TransformPolygon polygon) {
        List<Point2D> pointList = new ArrayList<>();

        polygon.getPointList().forEach(point -> {
            double[][] matrixPoint = matrixMultiplier.multiply(polygon.getMatrix(),
                matrixHelper.convertPointToMatrix(point));
            matrixPoint[0][0] = halfScreenX + matrixPoint[0][0] * polygonScale;
            matrixPoint[1][0] = halfScreenY - matrixPoint[1][0] * polygonScale;
            pointList.add(matrixHelper.convertMatrixToPoint(matrixPoint));
        });

        polygon.getPolygon().getPoints().setAll(getUngroupedPointList(pointList));
    }

    private List<Double> getUngroupedPointList(List<Point2D> groupedPointList) {
        List<Double> pointList = new ArrayList<>();
        groupedPointList.forEach(point -> {
            pointList.add(point.getX());
            pointList.add(point.getY());
        });
        return pointList;
    }
}
