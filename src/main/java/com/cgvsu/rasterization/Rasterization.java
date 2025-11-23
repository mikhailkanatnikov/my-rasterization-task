package com.cgvsu.rasterization;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class Rasterization {



    public static void drawArc(
            final GraphicsContext graphicsContext,
            final double centerX, final double centerY,
            final double radius,
            double startAngle, double endAngle,  // убрал final
            final Color startColor, final Color endColor)
    {
        final PixelWriter pixelWriter = graphicsContext.getPixelWriter();

        // ИНВЕРТИРУЕМ УГЛЫ для JavaFX (ось Y вниз)
        startAngle = -startAngle;
        endAngle = -endAngle;

        // НОРМАЛИЗУЕМ УГЛЫ - чтобы startAngle был меньше endAngle
        if (startAngle > endAngle) {
            double temp = startAngle;
            startAngle = endAngle;
            endAngle = temp;
        }

        final double totalAngle = endAngle - startAngle;
        final double angleStep = 1.0 / radius;

        for (double angle = startAngle; angle <= endAngle; angle += angleStep) {
            double x = centerX + radius * Math.cos(angle);
            double y = centerY + radius * Math.sin(angle);
            double t = (angle - startAngle) / totalAngle;

            pixelWriter.setColor((int) Math.round(x), (int) Math.round(y),
                    interpolate(startColor, endColor, t));
        }

    }

    public static Color interpolate(Color startColor, Color endColor, double t){

        double r = startColor.getRed() + (endColor.getRed() - startColor.getRed()) * t;
        double g = startColor.getGreen() + (endColor.getGreen() - startColor.getGreen()) * t;
        double b = startColor.getBlue() + (endColor.getBlue() - startColor.getBlue()) * t;
        return new Color(r, g, b, 1.0);
    }

}
