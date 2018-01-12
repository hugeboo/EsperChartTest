package ru.dotkit.esperchart.model;

/**
 * Created by ssv on 11.01.2018.
 */

public final class Point {

    private final int _time;
    private final double _value;
    private final double _volume;

    public int getTime() {
        return _time;
    }

    public double getValue() {
        return _value;
    }

    public double getVolume() {
        return _volume;
    }

    public Point(int time, double value) {
        this(time, value, 1);
    }

    public Point(int time, double value, double volume) {
        if (time < 0) {
            throw new IllegalArgumentException("time < 0");
        }
        _time = time;
        _value = value;
        _volume = volume;
    }
}
