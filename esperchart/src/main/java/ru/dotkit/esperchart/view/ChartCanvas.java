package ru.dotkit.esperchart.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.DisplayMetrics;

/**
 * Created by ssv on 12.01.2018.
 */

public final class ChartCanvas {

    //private final Context _context;
    //private final DisplayMetrics _displayMetrics;
    private final Canvas _canvas;
    private final ChartCanvasMetrics _metrics;

    public ChartCanvasMetrics getMetrics() {
        return _metrics;
    }

    public ChartCanvas(/*Context context,*/ Canvas canvas, ChartCanvasMetrics metrics) {
        _canvas = canvas;
        _metrics = metrics;
        //_context = context;
        //_displayMetrics = _context.getResources().getDisplayMetrics();
    }

//    private float dpToPixel(float dp){
//        return dp * (_displayMetrics.densityDpi / 160f);
//    }

    public void drawCircle(int time, double value, float radius, Paint paint) {
        drawCircle(time, value, radius, true, paint);
    }

    public void drawCircle(int time, double value, float radius, boolean leftValue, Paint paint) {
        float x = _metrics.timeToX(time);
        float y = leftValue ? _metrics.leftValueToY(value) : _metrics.rightValueToY(value);
        _canvas.drawCircle(x, y, radius, paint);
    }

    public void drawLines(int[] times, double[] values, Paint paint) {
        drawLines(times, values, true, paint);
    }

    public void drawLines(int[] times, double[] values, boolean leftValue, Paint paint) {
        for (int i = 0; i < times.length - 1; i++) {
            _canvas.drawLine(
                    _metrics.timeToX(times[i]),
                    leftValue ? _metrics.leftValueToY(values[i]) : _metrics.rightValueToY(values[i]),
                    _metrics.timeToX(times[i + 1]),
                    leftValue ? _metrics.leftValueToY(values[i + 1]) : _metrics.rightValueToY(values[i + 1]),
                    paint);
        }
    }
}
