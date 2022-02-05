package com.creseliana;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    public static void main(String[] args) {
        // write your code here
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Point2D point1 = new Point2D(90, 90);
        Point2D point2 = new Point2D(150, 110);
        Point2D point3 = new Point2D(210, 90);
        Point2D point4 = new Point2D(210, 210);
        Point2D point5 = new Point2D(150, 190);
        Point2D point6 = new Point2D(90, 210);
//        double[] points = {
//                90, 90,
//                150, 110,
//                210, 90,
//                210, 210,
//                150, 190,
//                90, 210
//        };
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(
                point1.getX(), point1.getY(),
                point2.getX(), point2.getY(),
                point3.getX(), point3.getY(),
                point4.getX(), point4.getY(),
                point5.getX(), point5.getY(),
                point6.getX(), point6.getY());
        polygon.setStroke(Color.BLACK);
        polygon.setFill(Color.WHITE);

//        polygon.tra;


        //Creating a Group object
        Group root = new Group(polygon);

        //Creating a scene object
        Scene scene = new Scene(root, 300, 300);

        //Setting title to the Stage
        stage.setTitle("Affine transformations");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }
}
