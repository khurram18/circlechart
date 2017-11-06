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
    circleChart.setCircleChartDataSource(emptyChartDataSource);
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
        switch (sectorIndex) {
            case 0: {
                return ContextCompat.getColor(MainActivity.this, R.color.group_a_fill);
            }
            case 1: {
                return ContextCompat.getColor(MainActivity.this, R.color.group_b_fill);
            }
            case 2: {
                return ContextCompat.getColor(MainActivity.this, R.color.group_c_fill);
            }
            case 3: {
                return ContextCompat.getColor(MainActivity.this, R.color.group_d_fill);
            }
            case 4: {
                return ContextCompat.getColor(MainActivity.this, R.color.group_e_fill);
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
        }
        return "";
    }

    @Override
    public float titleTextSize(CircleChart circleChart) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics());
    }
};
}
