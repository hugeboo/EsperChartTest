package ru.dotkit.esperchart.view.lines;

import android.graphics.Color;
import android.graphics.Paint;

import ru.dotkit.esperchart.model.BarPoint;
import ru.dotkit.esperchart.model.BarPointSequence;
import ru.dotkit.esperchart.model.PointSequence;
import ru.dotkit.esperchart.view.ChartCanvas;

/**
 * Created by ssv on 12.01.2018.
 */

public abstract class Line {

    private final PointSequence _pointSequence;
    private BarPointSequence _barPointSequence;
    private final Paint _forePaint;

    protected BarPointSequence getBarPointSequence(){
        return _barPointSequence;
    }

    protected Paint getForePaint(){
        return _forePaint;
    }

    public Line(PointSequence pointSequence)
    {
        _pointSequence = pointSequence;
        _forePaint = new Paint();
        _forePaint.setAntiAlias(true);
        _forePaint.setColor(Color.BLUE);
        _forePaint.setStrokeWidth(1);
    }

    public final void RefreshData(int beginTime, int endTime, int interval) {
        _barPointSequence = new BarPointSequence(_pointSequence, beginTime, endTime, interval);
    }

    public final void Draw(ChartCanvas canvas) {
        if (_barPointSequence.getInterval() != canvas.getMetrics().intervalWidthTime) {
            throw new IllegalArgumentException(
                    "Bar interval mismatch: barPointSequence.Interval=" +
                            _barPointSequence.getInterval() +
                            ", canvas.Interval=" + canvas.getMetrics().intervalWidthTime);
        }

        OnDraw(canvas);
    }

    protected abstract void OnDraw(ChartCanvas canvas);
}
