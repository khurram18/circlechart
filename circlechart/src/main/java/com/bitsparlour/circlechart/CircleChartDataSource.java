package com.bitsparlour.circlechart;

/**
 * Created by Khurram Shehzad on 18 October 2017
 */

public interface CircleChartDataSource {
int numberOfSectors(CircleChart circleChart);
int numberOfTracks(CircleChart circleChart);
int guideLinesColor(CircleChart circleChart);
int fillColorForSector(CircleChart circleChart, int sectorIndex);
boolean shouldFillSegment(CircleChart circleChart, int trackIndex, int sectorIndex);
boolean shouldDrawTrack(CircleChart circleChart, int trackIndex);
String titleForSector(CircleChart circleChart, int sectorIndex);
// In pixels
float titleTextSize(CircleChart circleChart);
}
