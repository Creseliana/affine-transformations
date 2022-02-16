package com.creseliana.cglab.service;

import com.creseliana.cglab.model.TransformPolygon;
import java.util.List;
import javafx.geometry.Point2D;

public interface TransformationsExecutor {

    void translate(TransformPolygon polygon, double x, double y);

    void dilate(TransformPolygon polygon, double index);

    void rotate(TransformPolygon polygon, double degree);

    void reflect(TransformPolygon polygon, boolean x, boolean y);

    void reset(TransformPolygon polygon, List<Point2D> pointList);
}
