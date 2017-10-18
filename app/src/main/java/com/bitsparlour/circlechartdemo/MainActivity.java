package com.bitsparlour.circlechartdemo;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bitsparlour.circlechart.CircleChart;
import com.bitsparlour.circlechart.CircleChartDataSource;

public class MainActivity extends AppCompatActivity {

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    circleChart = (CircleChart) findViewById(R.id.circleChart);
    circleChart.setCircleChartDataSource(circleChartDataSource);
}
private CircleChartDataSource circleChartDataSource = new CircleChartDataSource() {
    @Override
    public int numberOfSectors(CircleChart circleChart) {
        return 6;
    }

    @Override
    public int numberOfTracks(CircleChart circleChart) {
        return 5;
    }

    @Override
    public int guideLinesColor() {
        return ContextCompat.getColor(MainActivity.this, android.R.color.holo_red_dark);
    }
};
private CircleChart circleChart;
}
