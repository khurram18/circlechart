package com.bitsparlour.circlechart;

/**
 * Created by Khurram Shehzad on 18 October 2017
 */

public interface CircleChartDataSource {
int numberOfSectors(CircleChart circleChart);
int numberOfTracks(CircleChart circleChart);
int guideLinesColor();
}
