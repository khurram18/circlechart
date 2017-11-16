package com.bitsparlour.circlechartdemo;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;

import com.bitsparlour.circlechart.CircleChart;
import com.bitsparlour.circlechart.CircleChartDataSource;

public class MainActivity extends AppCompatActivity {

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    CircleChart circleChart = findViewById(R.id.circleChart);
    circleChart.setCircleChartDataSource(circleChartDataSource);
}
private CircleChartDataSource emptyChartDataSource = new CircleChartDataSource() {
    @Override
    public int numberOfSectors(CircleChart circleChart) {
        return 5;
    }

    @Override
    public int numberOfTracks(CircleChart circleChart) {
        return 8;
    }

    @Override
    public int guideLinesColor(CircleChart circleChart) {
        return ContextCompat.getColor(MainActivity.this, android.R.color.darker_gray);
    }

    @Override
    public int fillColorForSector(CircleChart circleChart, int sectorIndex) {
        return ContextCompat.getColor(MainActivity.this, android.R.color.transparent);
    }

    @Override
    public boolean shouldFillSegment(CircleChart circleChart, int trackIndex, int sectorIndex) {
        return false;
    }

    @Override
    public boolean shouldDrawTrack(CircleChart circleChart, int trackIndex) {
        return trackIndex > 1 && trackIndex < 7;
    }

    @Override
    public String titleForSector(CircleChart circleChart, int sectorIndex) {
        return null;
    }

    @Override
    public float titleTextSize(CircleChart circleChart) {
        return 0;
    }
};
private CircleChartDataSource circleChartDataSource = new CircleChartDataSource() {
    @Override
    public int numberOfSectors(CircleChart circleChart) {
        return 6;
    }

    @Override
    public int numberOfTracks(CircleChart circleChart) {
        return 8;
    }

    @Override
    public int guideLinesColor(CircleChart circleChart) {
        return ContextCompat.getColor(MainActivity.this, android.R.color.darker_gray);
    }

    @Override
    public int fillColorForSector(CircleChart circleChart, int sectorIndex) {
        switch (sectorIndex) {
            case 0: {
                return ContextCompat.getColor(MainActivity.this, R.color.summary_chart_fill_0);
            }
            case 1: {
                return ContextCompat.getColor(MainActivity.this, R.color.summary_chart_fill_1);
            }
            case 2: {
                return ContextCompat.getColor(MainActivity.this, R.color.summary_chart_fill_2);
            }
            case 3: {
                return ContextCompat.getColor(MainActivity.this, R.color.summary_chart_fill_3);
            }
            case 4: {
                return ContextCompat.getColor(MainActivity.this, R.color.summary_chart_fill_4);
            }
            case 5: {
                return ContextCompat.getColor(MainActivity.this, R.color.summary_chart_fill_5);
            }
        }
        return ContextCompat.getColor(MainActivity.this, android.R.color.holo_green_dark);
    }

    @Override
    public boolean shouldFillSegment(CircleChart circleChart, int trackIndex, int sectorIndex) {

        switch (sectorIndex) {
            case 0: {
                return trackIndex == 0 || trackIndex == 1 || trackIndex == 2 || trackIndex == 3 || trackIndex == 4 || trackIndex == 5;
            }
            case 1: {
                return trackIndex == 0 || trackIndex == 1;
            }
            case 2: {
                return trackIndex == 0 || trackIndex == 1 || trackIndex == 2;
            }
            case 3: {
                return trackIndex == 0 || trackIndex == 1 || trackIndex == 2 || trackIndex == 3 || trackIndex == 4;
            }
            case 4: {
                return trackIndex == 0 || trackIndex == 1 || trackIndex == 2 || trackIndex == 3;
            }
            case 5: {
                return trackIndex == 0 || trackIndex == 1 || trackIndex == 2;
            }
        }
        return false;
    }

    @Override
    public boolean shouldDrawTrack(CircleChart circleChart, int trackIndex) {
        return trackIndex > 1 && trackIndex < 7;
    }

    @Override
    public String titleForSector(CircleChart circleChart, int sectorIndex) {
        switch (sectorIndex) {
            case 0: {
                return "Group A";
            }
            case 1: {
                return "Group B";
            }
            case 2: {
                return "Group C";
            }
            case 3: {
                return "Group D";
            }
            case 4: {
                return "Group E";
            }
            case 5: {
                return "Group F";
            }
        }
        return "";
    }

    @Override
    public float titleTextSize(CircleChart circleChart) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics());
    }
};
}
