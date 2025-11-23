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

        // 1. Простая дуга - четверть круга от красного к синему
        Rasterization.drawArc(canvas.getGraphicsContext2D(), 100, 100, 50, 0, Math.PI/2, Color.RED, Color.BLUE);

        // 2. Полукруг - от зеленого к желтому
        Rasterization.drawArc(canvas.getGraphicsContext2D(), 300, 150, 80, 0, Math.PI, Color.GREEN, Color.ORANGE);

        // 3.
        // от 0 до 3п/2
        Rasterization.drawArc(canvas.getGraphicsContext2D(), 500, 200, 65, 0,3*Math.PI/2, Color.RED, Color.BLUE);

        // 4.
        // от 0 до 3п/4
        Rasterization.drawArc(canvas.getGraphicsContext2D(), 550, 400, 70, 3*Math.PI/2,2*Math.PI, Color.RED, Color.BLUE);


    }

}