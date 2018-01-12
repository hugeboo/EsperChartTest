package ru.dotkit.esperchart.view.lines;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.List;

import ru.dotkit.esperchart.model.BarPoint;
import ru.dotkit.esperchart.model.BarPointSequence;
import ru.dotkit.esperchart.model.PointSequence;
import ru.dotkit.esperchart.view.ChartCanvas;

/**
 * Created by ssv on 12.01.2018.
 */

public final class DotLine extends Line {

    public int dotRadius = 2;
    public boolean withLines = true;

    public DotLine(PointSequence pointSequence) {
        super(pointSequence);
    }

    public void OnDraw(ChartCanvas canvas) {

        List<BarPoint> points = getBarPointSequence().getPoints();
        if (points.size() > 0) {
            if (withLines) {
                int[] times = new int[points.size()];
                double[] values = new double[points.size()];
                for (int i = 0; i < points.size(); i++) {
                    BarPoint pt = points.get(i);
                    times[i] = pt.getTime();
                    values[i] = pt.getOpen();
                }
                canvas.drawLines(times, values, getForePaint());
            }
            for (BarPoint pt : points) {
                canvas.drawCircle(pt.getTime(), pt.getOpen(), dotRadius, getForePaint());
            }
        }
    }
}
