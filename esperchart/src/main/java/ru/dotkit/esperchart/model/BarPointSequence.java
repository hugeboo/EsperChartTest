package ru.dotkit.esperchart.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssv on 11.01.2018.
 */

public final class BarPointSequence {

    private final ArrayList<BarPoint> _barPoints;
    private final int _interval;
    private BarPoint _lastBarPoint;
    private int _realBeginTime;
    private int _realEndTime;

    public int getRealBeginTime() {
        return _realBeginTime;
    }

    public int getRealEndTime() {
        return _realEndTime;
    }

    public List<BarPoint> getPoints() {
        return _barPoints;
    }

    public int getInterval() {
        return _interval;
    }

    public int getSize(){return _barPoints.size();};

    public BarPointSequence(PointSequence src, int beginTime, int endTime, int interval) {
        if (interval < 1) {
            throw new IllegalArgumentException("Bad interval: " + interval);
        }
        _barPoints = new ArrayList<BarPoint>();
        _interval = interval;
        addPointSequence(src, beginTime, endTime);
    }

    private void addPointSequence(PointSequence src, int beginTime, int endTime) {
        int bi = src.getPointIndexForBegin(beginTime);
        int ei = src.getPointIndexForEnd(endTime);
        if (bi >= 0 && ei >= 0) {
            for (int i = bi; i <= ei; i++) {
                addNextPoint(src.getPoint(i));
            }
        }
    }

    private void addNextPoint(Point p) {
        if (_lastBarPoint == null) {
            _realBeginTime = p.getTime();
            _lastBarPoint = new BarPoint((p.getTime() / _interval) * _interval);
            _lastBarPoint.addNextPoint(p);
            _barPoints.add(_lastBarPoint);

        } else {
            int lastPointTime = _lastBarPoint.getTime();
            if (p.getTime() < lastPointTime) {
                throw new IllegalArgumentException(
                        "Time too early: " + p.getTime() + ", expected >=" + lastPointTime);
            }
            if (p.getTime() >= lastPointTime + _interval) {
                _lastBarPoint = new BarPoint((p.getTime() / _interval) * _interval);
                _lastBarPoint.addNextPoint(p);
                _barPoints.add(_lastBarPoint);
            } else {
                _lastBarPoint.addNextPoint(p);
            }
        }
        _realEndTime = p.getTime();
    }
}
