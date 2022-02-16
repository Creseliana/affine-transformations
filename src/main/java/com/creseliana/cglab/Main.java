package com.creseliana.cglab;

import com.creseliana.cglab.model.TransformPolygon;
import com.creseliana.cglab.service.MatrixHelper;
import com.creseliana.cglab.service.MatrixMultiplier;
import com.creseliana.cglab.service.TransformationsExecutor;
import com.creseliana.cglab.service.impl.MatrixHelperImpl;
import com.creseliana.cglab.service.impl.MatrixMultiplierImpl;
import com.creseliana.cglab.service.impl.TransformationsExecutorImpl;
import java.util.List;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int SHIFT = 1;

    public static void main(String[] args) {
        launch(args);
    }

    private static List<Point2D> getInitialPolygonPoints() {
        Point2D point1 = new Point2D(-3, 4);
        Point2D point2 = new Point2D(0, 3);
        Point2D point3 = new Point2D(3, 4);
        Point2D point4 = new Point2D(3, -2);
        Point2D point5 = new Point2D(0, -1);
        Point2D point6 = new Point2D(-3, -2);
        return List.of(point1, point2, point3, point4, point5, point6);
    }

    private static String pointListToString(List<Double> pointList) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < pointList.size(); i = i + 2) {
            builder.append('[').append(pointList.get(i)).append(", ")
                .append(pointList.get(i + 1)).append(']');
        }
        return builder.toString();
    }

    @Override
    public void start(Stage stage) {
        final TransformPolygon currentPolygon = new TransformPolygon();
        final TransformationType[] type = new TransformationType[]{TransformationType.NO};
        MatrixMultiplier matrixMultiplier = new MatrixMultiplierImpl();
        MatrixHelper matrixHelper = new MatrixHelperImpl();
        TransformationsExecutor transformationsExecutor = new TransformationsExecutorImpl(
            matrixMultiplier, matrixHelper, 300, 300, 20);

        transformationsExecutor.reset(currentPolygon, getInitialPolygonPoints());

        Button resetButton = new Button("reset");
        resetButton.setPrefWidth(100);
        Button translateButton = new Button("translate");
        translateButton.setPrefWidth(100);
        translateButton.setLayoutX(100);
        Button dilateButton = new Button("dilate");
        dilateButton.setPrefWidth(100);
        dilateButton.setLayoutX(200);
        Button rotateButton = new Button("rotate");
        rotateButton.setPrefWidth(100);
        rotateButton.setLayoutX(300);
        Button reflectButton = new Button("reflect");
        reflectButton.setPrefWidth(100);
        reflectButton.setLayoutX(400);
        TextField textField = new TextField();
        textField.setEditable(false);
        textField.setLayoutY(30);
        textField.setPrefWidth(600);
        textField.setText(pointListToString(currentPolygon.getPolygon().getPoints()));

        Group root = new Group(currentPolygon.getPolygon(), textField,
            resetButton, translateButton, dilateButton, rotateButton, reflectButton);
        Scene scene = new Scene(root, 600, 600);
        stage.setTitle("Affine transformations");
        stage.setScene(scene);
        stage.show();

        translateButton.setOnAction(event -> type[0] = TransformationType.TRANSLATE);
        dilateButton.setOnAction(event -> type[0] = TransformationType.DILATE);
        rotateButton.setOnAction(event -> type[0] = TransformationType.ROTATE);
        reflectButton.setOnAction(event -> type[0] = TransformationType.REFLECT);
        resetButton.setOnAction(
            event -> {
                type[0] = TransformationType.NO;
                transformationsExecutor.reset(currentPolygon, getInitialPolygonPoints());
                textField.setText(pointListToString(currentPolygon.getPolygon().getPoints()));
            });

        EventHandler<KeyEvent> keyListener = (event) -> {
            if (event.getCode() == KeyCode.A) {
                switch (type[0]) {
                    case TRANSLATE:
                        transformationsExecutor.translate(currentPolygon, SHIFT * -1, 0);
                        break;
                }
            }

            if (event.getCode() == KeyCode.D) {
                switch (type[0]) {
                    case TRANSLATE:
                        transformationsExecutor.translate(currentPolygon, SHIFT, 0);
                        break;
                    case ROTATE:
                        transformationsExecutor.rotate(currentPolygon, 20);
                        break;
                    case REFLECT:
                        transformationsExecutor.reflect(currentPolygon, true, false);
                        break;
                }
            }

            if (event.getCode() == KeyCode.W) {
                switch (type[0]) {
                    case TRANSLATE:
                        transformationsExecutor.translate(currentPolygon, 0, SHIFT);
                        break;
                    case DILATE:
                        transformationsExecutor.dilate(currentPolygon, 1.25);
                        break;
                    case REFLECT:
                        transformationsExecutor.reflect(currentPolygon, false, true);
                        break;
                }
            }

            if (event.getCode() == KeyCode.S) {
                switch (type[0]) {
                    case TRANSLATE:
                        transformationsExecutor.translate(currentPolygon, 0, SHIFT * -1);
                        break;
                    case DILATE:
                        transformationsExecutor.dilate(currentPolygon, 0.75);
                        break;
                }
            }
            textField.setText(pointListToString(currentPolygon.getPolygon().getPoints()));
        };

        scene.addEventHandler(KeyEvent.KEY_RELEASED, keyListener);
    }

    private enum TransformationType {
        TRANSLATE,
        DILATE,
        ROTATE,
        REFLECT,
        NO
    }
}
