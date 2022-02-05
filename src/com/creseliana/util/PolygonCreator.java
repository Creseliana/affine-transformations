package com.creseliana.util;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

public interface PolygonCreator {

    Polygon create(Point2D... points);
}
