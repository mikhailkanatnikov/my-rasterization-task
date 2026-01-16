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
        //Rasterization.drawArc(canvas.getGraphicsContext2D(), 200, 100, 100, 0, Math.PI*2, Color.RED, Color.BLUE);

        //2. Пол окружности работает
        //Rasterization.drawArc(canvas.getGraphicsContext2D(), 400, 100, 50, 0, Math.PI, Color.RED, Color.BLUE);

        //3. Четверть окружности работает
        //Rasterization.drawArc(canvas.getGraphicsContext2D(), 600, 100, 85, 0, Math.PI/2, Color.BLUE, Color.RED);

        //4 пол дуги от -п/2 до п/2 //кривая интерполяция
        //Rasterization.drawArc(canvas.getGraphicsContext2D(), 600, 350, 85, -1*Math.PI/2, Math.PI/2, Color.BLUE, Color.RED);

        //5 3/4 КРУГА
        //Rasterization.drawArc(canvas.getGraphicsContext2D(), 600, 450, 85, 0, 3*Math.PI/2, Color.BLUE, Color.RED);

        //кусочек внизу
        //Rasterization.drawArc(canvas.getGraphicsContext2D(), 650, 500, 85, Math.PI/4, 3*Math.PI/4, Color.BLUE, Color.RED);


    }

}