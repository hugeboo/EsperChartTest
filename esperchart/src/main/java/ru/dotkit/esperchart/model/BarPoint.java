package ru.dotkit.esperchart.model;

/**
 * Created by ssv on 11.01.2018.
 */

public final class BarPoint {

    private final int _time;
    private double _open = Double.NaN;
    private double _high = Double.NaN;
    private double _low = Double.NaN;
    private double _close = Double.NaN;
    private double _volume = 0.0;

    public int getTime() {
        return _time;
    }

    public double getOpen() {
        return _open;
    }

    public double getHigh() {
        return _high;
    }

    public double getLow() {
        return _low;
    }

    public double getClose() {
        return _close;
    }

    public double getVolume() {
        return _volume;
    }

    public BarPoint(int time) {
        _time = time;
    }

    public boolean isValid() {
        return !Double.isNaN(_open) && !Double.isNaN(_close) &&
                !Double.isNaN(_high) && !Double.isNaN(_low) &&
                _high >= _low &&
                _open <= _high && _open >= _low &&
                _close <= _high && _close >= _low;
    }

    public void addNextPoint(Point p) {
        double v = p.getValue();
        double volume = p.getVolume();
        if (Double.isInfinite(v) || Double.isNaN(v)) {
            throw new IllegalArgumentException("Bad value: " + v);
        }
        if (Double.isInfinite(p.getVolume()) || Double.isNaN(volume) || volume < 0) {
            throw new IllegalArgumentException("Bad volume: " + volume);
        }
        if (Double.isNaN(_open)) _open = v;
        if (Double.isNaN(_low) || v < _low) _low = v;
        if (Double.isNaN(_high) || v > _high) _high = v;
        _close = v;
        _volume += volume;
    }
}
