package ru.dotkit.espercharttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.dotkit.esperchart.view.EsperChartView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EsperChartView chartView = new EsperChartView(this);
        setContentView(chartView);
        //setContentView(R.layout.activity_main);
    }
}
