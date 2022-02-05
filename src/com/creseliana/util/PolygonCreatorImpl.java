package com.creseliana.util;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

public class PolygonCreatorImpl implements PolygonCreator {

    @Override
    public Polygon create(Point2D... points) {
        double[] pointArray = new double[points.length * 2];

        for (int i = 0, j = 0; i < points.length; i++) {
            pointArray[j++] = points[i].getX();
            pointArray[j++] = points[i].getY();
        }

        return new Polygon(pointArray);
    }

    
}
