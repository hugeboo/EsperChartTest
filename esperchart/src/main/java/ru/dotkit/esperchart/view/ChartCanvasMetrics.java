package ru.dotkit.esperchart.view;

import android.graphics.RectF;

/**
 * Created by ssv on 12.01.2018.
 */

public final class ChartCanvasMetrics {

    public RectF bounds;              // прямоугольник экрана
    public double leftValueTop;       // максимальное значение по левой оси Y
    public double leftValueBottom;    // минимальное значение по левой оси Y
    public double rightValueTop;      // максимальное значение по правой оси Y
    public double rightValueBottom;   // минимальное значение по правой оси Y

    public float refIntervalX;        // экранная координата X начала опорного бара
    public int refIntervalTime;       // время опорного бара
    public float intervalWidth;       // экранная ширина бара
    public int intervalWidthTime;     // ширина бара в секундах

    private double xs() {
        return intervalWidthTime / intervalWidth; // screen to x scale
    }

    private double ysl() {
        return (leftValueTop - leftValueBottom) / bounds.height(); // screen to y scale
    }

    private double ysr() {
        return (rightValueTop - rightValueBottom) / bounds.height(); // screen to y scale
    }

    public int getTimeLeft() {
        return (int)(refIntervalTime - (refIntervalX - bounds.left) * xs());
    }

    public int getTimeRight() {
        return (int)(refIntervalTime - (refIntervalX - bounds.right) * xs());
    }

    public int xToTime(float x) {
        return (int)(refIntervalTime - (refIntervalX - x) * xs());
    }

    public float timeToX(int time) {
        return (float)(refIntervalX + (time - refIntervalTime) / xs());
    }

    public double yToLeftValue(float y) {
        return (bounds.bottom - y) * ysl();
    }

    public float leftValueToY(double value) {
        return (float)(bounds.bottom - value / ysl());
    }

    public double yToRightValue(float y) {
        return (bounds.bottom - y) * ysr();
    }

    public float rightValueToY(double value) {
        return (float)(bounds.bottom - value / ysr());
    }
}
