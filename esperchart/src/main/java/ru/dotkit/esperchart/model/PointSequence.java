package ru.dotkit.esperchart.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssv on 11.01.2018.
 */

public final class PointSequence {

    private final ArrayList<Point> _list;

    public int getBeginTime() {
        return !_list.isEmpty() ? _list.get(0).getTime() : 0;
    }

    public int getEndTime() {
        return !_list.isEmpty() ? _list.get(_list.size() - 1).getTime() : 0;
    }

    public PointSequence() {
        _list = new ArrayList<>();
    }

    public void addNextPoint(Point p) {
        if (p.getTime() < getEndTime()) {
            throw new IllegalArgumentException(
                    "Time too early: " + p.getTime() + ", expected >=" + getEndTime());
        }
        _list.add(p);
    }

    public void addNextPointRange(List<Point> points) {
        for (Point p : points) {
            addNextPoint(p);
        }
    }

    public Point getPoint(int index) {
        return _list.get(index);
    }

    public int getPointIndexForBegin(int time) {
        if (_list.size() == 0) {
            return -1;
        } else if (time > getEndTime()) {
            return -1;
        } else if (time <= getBeginTime()) {
            return 0;
        } else {
            int si = 0;
            int ei = _list.size() - 1;
            while (ei - si > 1) {
                int mi = (ei + si) / 2;
                Point m = _list.get(mi);
                if (time > m.getTime()) {
                    si = mi;
                } else if (time < m.getTime()) {
                    ei = mi;
                } else {
                    while (time == m.getTime() && mi > 0) {
                        m = _list.get(--mi);
                    }
                    return mi + 1;
                }
            }
            return _list.get(si).getTime() >= time ? si : ei;
        }
    }

    public int getPointIndexForEnd(int time) {
        if (_list.size() == 0) {
            return -1;
        } else if (time < getBeginTime()) {
            return -1;
        } else if (time >= getEndTime()) {
            return _list.size() - 1;
        } else {
            int si = 0;
            int ei = _list.size() - 1;
            while (ei - si > 1) {
                int mi = (ei + si) / 2;
                Point m = _list.get(mi);
                if (time > m.getTime()) {
                    si = mi;
                } else if (time < m.getTime()) {
                    ei = mi;
                } else {
                    while (time == m.getTime() && mi < _list.size() - 1) {
                        m = _list.get(++mi);
                    }
                    return mi - 1;
                }
            }
            return _list.get(ei).getTime() <= time ? ei : si;
        }
    }
}
