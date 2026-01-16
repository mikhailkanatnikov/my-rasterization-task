package com.cgvsu.rasterization;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class Rasterization {

    public static void drawArc(
            GraphicsContext gc,
            double centerX, double centerY,
            double radius,
            double startAngle, double endAngle,
            Color startColor, Color endColor) {

        PixelWriter pw = gc.getPixelWriter();

        int xc = (int) centerX;
        int yc = (int) centerY;
        int r = (int) radius;

        int x = 0;
        int y = r;
        int d = 3 - 2 * r;

        // Векторы начала и конца
        double ax = Math.cos(startAngle);
        double ay = Math.sin(startAngle);
        double bx = Math.cos(endAngle);
        double by = Math.sin(endAngle);

        int oA = octant(ax, ay);
        int oB = octant(bx, by);

        while (x <= y) {
            plot(pw, xc, yc, x, y, ax, ay, bx, by, oA, oB, startColor, endColor);
            plot(pw, xc, yc, y, x, ax, ay, bx, by, oA, oB, startColor, endColor);
            plot(pw, xc, yc, -x, y, ax, ay, bx, by, oA, oB, startColor, endColor);
            plot(pw, xc, yc, -y, x, ax, ay, bx, by, oA, oB, startColor, endColor);
            plot(pw, xc, yc, -x, -y, ax, ay, bx, by, oA, oB, startColor, endColor);
            plot(pw, xc, yc, -y, -x, ax, ay, bx, by, oA, oB, startColor, endColor);
            plot(pw, xc, yc, x, -y, ax, ay, bx, by, oA, oB, startColor, endColor);
            plot(pw, xc, yc, y, -x, ax, ay, bx, by, oA, oB, startColor, endColor);

            if (d < 0) {
                d += 4 * x + 6;
            } else {
                d += 4 * (x - y) + 10;
                y--;
            }
            x++;
        }
    }

    // ===== функция, которая возвращает номер октанта (0...7) =====
    private static int octant(double x, double y) {
        if (x >= 0 && y >= 0) {
            return (x >= y) ? 0 : 1;
        }
        if (x < 0 && y >= 0) {
            return (-x <= y) ? 2 : 3;
        }
        if (x < 0 && y < 0) {
            return (-x >= -y) ? 4 : 5;
        }
        // x >= 0 && y < 0
        return (x <= -y) ? 6 : 7;
    }

    // ===== ПРОВЕРКА + ОТРИСОВКА =====
    private static void plot(
            PixelWriter pw,
            int xc, int yc, int dx, int dy,
            double ax, double ay,
            double bx, double by,
            int oA, int oB,
            Color c1, Color c2) {

        int oP = octant(dx, dy);

        // Проверка попадания в диапазон октантов
        boolean inRange;
        if (oA <= oB) {
            inRange = oP >= oA && oP <= oB;
        } else {
            inRange = oP >= oA || oP <= oB;
        }
        if (!inRange) return;

        // Уточнение границ через векторное произведение
        if (oP == oA) {
            double mz = ax * dy - ay * dx; // OA × OP
            if (mz < 0) return;
        }
        if (oP == oB) {
            double nz = dx * by - dy * bx; // OP × OB
            if (nz < 0) return;
        }

        // ===== ПРОСТАЯ ИНТЕРПОЛЯЦИЯ "В ЛОБ" =====
        int total = (oB - oA + 8) % 8;
        if (total == 0) total = 8;

        int pos = (oP - oA + 8) % 8;

        double t = (double) pos / total;

        pw.setColor(xc + dx, yc + dy, interpolate(c1, c2, t));
    }

    // ===== ИНТЕРПОЛЯЦИЯ ЦВЕТА =====
    private static Color interpolate(Color a, Color b, double t) {
        t = Math.max(0, Math.min(1, t));
        return new Color(
                a.getRed() + (b.getRed() - a.getRed()) * t,
                a.getGreen() + (b.getGreen() - a.getGreen()) * t,
                a.getBlue() + (b.getBlue() - a.getBlue()) * t,
                1.0
        );
    }
}
