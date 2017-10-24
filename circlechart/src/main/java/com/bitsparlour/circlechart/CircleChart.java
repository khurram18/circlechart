package com.bitsparlour.circlechart;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Khurram Shehzad on 18 October 2017
 */
// https://code.tutsplus.com/tutorials/creating-and-publishing-an-android-library--cms-24582
public class CircleChart extends View {
public CircleChart(Context context) {
    super(context);
    init();
}

public CircleChart(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init();
}

public CircleChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
}

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public CircleChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init();
}
public CircleChartDataSource getCircleChartDataSource() { return circleChartDataSource; }
public void setCircleChartDataSource(CircleChartDataSource circleChartDataSource) {
    this.circleChartDataSource = circleChartDataSource;
    reload();
    invalidate();
}

@Override
protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    final int width = getWidth();
    final int height = getHeight();
    final int center = Math.min(width, height) / 2;
    final int radius = center - 50;
    final double angle = (2 * Math.PI) / sectors;
    final float trackRadius = radius / tracks;

    for (int sector = 0; sector < sectors; ++sector) {

        for (int track = 0; track < tracks; ++track) {

            if (fillSegments[sector][track]) {
                drawSegment(center,
                        center,
                        trackRadius * track,  // lower radius
                        trackRadius * (track + 1),  // upper radius
                        (float) (angle * sector),  // segment start angle
                        (float) (angle * (sector + 1)), // segment end angle
                        canvas,
                        sectorFillColors[sector]);
            }
        }
    }

    guidelinePaint.reset();
    guidelinePaint.setStyle(Paint.Style.STROKE);
    guidelinePaint.setColor(guideLinesColor);
    guidelinePaint.setStrokeWidth(2);

    // Draw sectors
    for (int i = 0; i < sectors; ++i) {
        path.reset();
        path.moveTo(center, center);
        path.lineTo((float) (center + (radius * Math.cos(angle * i))), (float) (center + (radius * Math.sin(angle * i))));
        canvas.drawPath(path, guidelinePaint);
    }
    // Draw tracks
    for (int i = 0; i < tracks; ++i) {
        if (drawTracks[i]) {
            path.reset();
            path.addCircle(center, center, trackRadius * i, Path.Direction.CCW);
            canvas.drawPath(path, guidelinePaint);
        }
    }
    guidelinePaint.setStyle(Paint.Style.STROKE);
}
private void drawSegment(final float cx,
                         final float cy,
                         final float lowerRadius,
                         final float upperRadius,
                         final float segmentStartAngle,
                         final float segmentEndAngle,
                         Canvas canvas,
                         final int fillColor) {

    guidelinePaint.reset();
    guidelinePaint.setStyle(Paint.Style.FILL_AND_STROKE);
    guidelinePaint.setStrokeWidth(2);
    guidelinePaint.setColor(fillColor);
    final float da = (float) ((Math.PI / 180) * 0.25);
    float currentAngle = segmentStartAngle;
    while (currentAngle <= segmentEndAngle) {

        path.reset();

        float endAngle = currentAngle + da;

        final float startX = (float) (cx + (lowerRadius * Math.cos(currentAngle)));
        final float startY = (float) (cy + (lowerRadius * Math.sin(currentAngle)));

        final float endX = (float) (cx + (lowerRadius * Math.cos(endAngle)));
        final float endY = (float) (cy + (lowerRadius * Math.sin(endAngle)));

        path.moveTo(startX, startY);
        path.lineTo(endX, endY);

        final float upperStartX =  (float) (cx+ (upperRadius * Math.cos(endAngle)));
        final float upperStartY = (float) (cy + (upperRadius * Math.sin(endAngle)));

        final float upperEndX =  (float) (cx + (upperRadius * Math.cos(currentAngle)));
        final float upperEndY = (float) (cy + (upperRadius * Math.sin(currentAngle)));

        path.lineTo(upperStartX, upperStartY);
        path.lineTo(upperEndX, upperEndY);

        path.lineTo(startX, startY);
        path.close();

        canvas.drawPath(path, guidelinePaint);

        currentAngle += da;
    }
}
private void init() {
    guidelinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    path = new Path();
}
private void reload() {
    if (circleChartDataSource != null) {
        sectors = circleChartDataSource.numberOfSectors(this);
        tracks = circleChartDataSource.numberOfTracks(this);
        guideLinesColor = circleChartDataSource.guideLinesColor();
        sectorFillColors = new int[sectors];
        fillSegments = new boolean[sectors][tracks];
        drawTracks = new boolean[tracks];
        for (int sector = 0; sector < sectors; ++sector) {
            sectorFillColors[sector] = circleChartDataSource.fillColorForSector(sector);
            for (int track = 0; track < tracks; ++track) {
                fillSegments[sector][track] = circleChartDataSource.shouldFillSegment(sector, track);
            }
        }
        for (int track = 0; track < tracks; ++track) {
            drawTracks[track] = circleChartDataSource.shouldDrawTrack(track);
        }
    }
}
private Path path;
private Paint guidelinePaint;
private int sectors = 1;
private int tracks = 1;
private CircleChartDataSource circleChartDataSource;
private int[] sectorFillColors;
private boolean[][] fillSegments;
private boolean[] drawTracks;
private int guideLinesColor;
}
