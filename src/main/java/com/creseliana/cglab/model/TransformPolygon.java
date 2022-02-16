package com.creseliana.cglab.model;

import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransformPolygon {

    private Polygon polygon;

    private List<Point2D> pointList;

    private double degree = 0;

    private double[][] matrix = new double[][]
        {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
}
