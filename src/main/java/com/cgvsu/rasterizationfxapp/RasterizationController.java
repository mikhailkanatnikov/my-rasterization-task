package com.cgvsu.rasterizationfxapp;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;

import com.cgvsu.rasterization.*;
import javafx.scene.paint.Color;

public class RasterizationController {

    @FXML
    AnchorPane anchorPane;
    @FXML
    private Canvas canvas;

    @FXML
    private void initialize() {
        anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue()));
        anchorPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));

        // 1. Полная окружность
        Rasterization.drawArc(canvas.getGraphicsContext2D(), 200, 100, 100, 0, Math.PI*2, Color.RED, Color.BLUE);

        //2. Пол окружности
        Rasterization.drawArc(canvas.getGraphicsContext2D(), 400, 100, 50, 0, Math.PI, Color.RED, Color.GREEN);

        //3. Четверть окружности
        Rasterization.drawArc(canvas.getGraphicsContext2D(), 600, 100, 85, 0, Math.PI/2, Color.BLUE, Color.PINK);

        //4. Отрицательный угол
        Rasterization.drawArc(canvas.getGraphicsContext2D(), 600, 400, 85, Math.PI/2, 0, Color.BLUE, Color.PINK);

    }

}