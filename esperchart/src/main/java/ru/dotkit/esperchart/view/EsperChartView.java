package ru.dotkit.esperchart.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.view.View;

import ru.dotkit.esperchart.model.Point;
import ru.dotkit.esperchart.model.PointSequence;
import ru.dotkit.esperchart.view.lines.DotLine;

/**
 * Created by ssv on 11.01.2018.
 */

public final class EsperChartView extends View {

    public EsperChartView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas c) {
        super.onDraw(c);

        RectF bounds = new RectF(10,10,100,100);

        c.clipRect(bounds);
        c.drawARGB(80, 102, 204, 255);

        ChartCanvasMetrics cm = new ChartCanvasMetrics();
        cm.bounds = bounds;
        cm.leftValueTop = 100;
        cm.leftValueBottom = 0;
        cm.rightValueTop = 100;
        cm.rightValueBottom = 0;
        cm.refIntervalX = 90;
        cm.refIntervalTime = 1000;
        cm.intervalWidth = 10;
        cm.intervalWidthTime = 100;

        ChartCanvas cc = new ChartCanvas(c, cm);

        PointSequence ps = new PointSequence();
        ps.addNextPoint(new Point(50,10));
        ps.addNextPoint(new Point(150,20));
        ps.addNextPoint(new Point(250,30));
        ps.addNextPoint(new Point(350,40));
        ps.addNextPoint(new Point(450,20));
        ps.addNextPoint(new Point(550,20));
        ps.addNextPoint(new Point(650,70));
        ps.addNextPoint(new Point(750,80));
        ps.addNextPoint(new Point(850,90));
        ps.addNextPoint(new Point(950,100));

        DotLine line = new DotLine(ps);
        line.RefreshData(0,850,100);
        line.Draw(cc);
    }

    public float convertDpToPixel(Context context, float dp) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * (metrics.densityDpi / 160f);
    }
}
