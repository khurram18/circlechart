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
    // Draw sectors
    double angle = (2 * Math.PI) / sectors;
    for (int i = 0; i < sectors; ++i) {
        path.reset();
        path.moveTo(center, center);
        path.lineTo((float) (center + (radius * Math.cos(angle * i))), (float) (center + (radius * Math.sin(angle * i))));
        canvas.drawPath(path, guidelinePaint);
    }
    // Draw tracks
    float trackRadius = radius / tracks;
    for (int i = 1; i <= tracks; ++i) {
        path.reset();
        path.addCircle(center, center, trackRadius * i, Path.Direction.CCW);
        canvas.drawPath(path, guidelinePaint);
    }
    guidelinePaint.setStyle(Paint.Style.STROKE);
}
private void init() {
    guidelinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    guidelinePaint.setStrokeWidth(2);
    path = new Path();
}
private void reload() {
    if (circleChartDataSource != null) {
        sectors = circleChartDataSource.numberOfSectors(this);
        tracks = circleChartDataSource.numberOfTracks(this);
        guidelinePaint.setColor(circleChartDataSource.guideLinesColor());
        guidelinePaint.setStyle(Paint.Style.STROKE);
    }
}
private Path path;
private Paint guidelinePaint;
private int sectors = 1;
private int tracks = 1;
private CircleChartDataSource circleChartDataSource;
}
