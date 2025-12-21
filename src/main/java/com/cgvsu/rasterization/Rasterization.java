package com.cgvsu.rasterization;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class Rasterization {


    public static void drawArc(
            final GraphicsContext graphicsContext,
            final double centerX, final double centerY,
            final double radius,
            double startAngle, double endAngle,
            final Color startColor, final Color endColor) {

        final PixelWriter pixelWriter = graphicsContext.getPixelWriter();

        int xc = (int) Math.round(centerX);
        int yc = (int) Math.round(centerY);
        int r = (int) Math.round(radius);


        if (endAngle < startAngle) {
            endAngle += 2 * Math.PI;
        }

        int x = 0;
        int y = r;
        int d = 3 - 2 * r;

        while (x <= y) {

            drawPointIfInArc(pixelWriter, xc, yc, x, y, startAngle, endAngle, startColor, endColor);
            drawPointIfInArc(pixelWriter, xc, yc, y, x, startAngle, endAngle, startColor, endColor);
            drawPointIfInArc(pixelWriter, xc, yc, -x, y, startAngle, endAngle, startColor, endColor);
            drawPointIfInArc(pixelWriter, xc, yc, -y, x, startAngle, endAngle, startColor, endColor);
            drawPointIfInArc(pixelWriter, xc, yc, -x, -y, startAngle, endAngle, startColor, endColor);
            drawPointIfInArc(pixelWriter, xc, yc, -y, -x, startAngle, endAngle, startColor, endColor);
            drawPointIfInArc(pixelWriter, xc, yc, x, -y, startAngle, endAngle, startColor, endColor);
            drawPointIfInArc(pixelWriter, xc, yc, y, -x, startAngle, endAngle, startColor, endColor);

            if (d < 0) {
                d = d + 4 * x + 6;
            } else {
                d = d + 4 * (x - y) + 10;
                y--;
            }
            x++;
        }
    }

    private static void drawPointIfInArc(
            PixelWriter pixelWriter,
            int xc, int yc, int dx, int dy,
            double startAngle, double endAngle,
            Color startColor, Color endColor) {

        int px = xc + dx;
        int py = yc + dy;


        double angle = Math.atan2(dy, dx);
        if (angle < 0) angle += 2 * Math.PI;


        if (angle >= startAngle && angle <= endAngle) {
            double delta = (angle - startAngle) / (endAngle - startAngle);
            pixelWriter.setColor(px, py, interpolate(startColor, endColor, delta));
        }
    }


    public static Color interpolate(Color startColor, Color endColor, double delta) {

        //getRed --> 1.0
        double r = startColor.getRed() + (endColor.getRed() - startColor.getRed()) * delta;
        double g = startColor.getGreen() + (endColor.getGreen() - startColor.getGreen()) * delta;
        double b = startColor.getBlue() + (endColor.getBlue() - startColor.getBlue()) * delta;
        return new Color(r, g, b, 1.0);
    }

}
