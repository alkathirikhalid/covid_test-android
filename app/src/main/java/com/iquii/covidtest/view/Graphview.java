package com.iquii.covidtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.iquii.covidtest.model.entity.DataPoint;

import java.util.List;

public class Graphview extends View {
    public Graphview(Context context) {
        super(context);
        configurePaints();
    }

    public Graphview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        configurePaints();
    }

    private List<DataPoint> dataSet;
    private int xMin = 0;
    private int xMax = 30;
    private int yMax = 0;
    private int yMin = 0;

    private Paint dataPointPaint = new Paint();
    private Paint dataPointFillPaint = new Paint();
    private Paint dataPointLinePaint = new Paint();
    private Paint axisLinePaint = new Paint();




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i = 0; i < dataSet.size(); i++){
            DataPoint current = dataSet.get(i);
            float realX = convertToRealX(current.getX());
            float realY = convertToRealY(current.getY());

            if(i < dataSet.size() -1){
                DataPoint nextPoint = dataSet.get(i+1);
                float startX = convertToRealX(current.getX());
                float startY = convertToRealY(current.getY());
                float endX = convertToRealX(nextPoint.getX());
                float endY = convertToRealY(nextPoint.getY());
                canvas.drawLine(startX,startX,endX,endY,dataPointLinePaint);
            }
            canvas.drawCircle(realX,realY,7f, dataPointFillPaint);
            canvas.drawCircle(realX,realY,7f,dataPointPaint);
        }
        canvas.drawLine(0f,0f,0f, getHeight(),axisLinePaint);
        canvas.drawLine(0f,getHeight(),getWidth(),getHeight(),axisLinePaint);

    }

    public void setData(List<DataPoint> data){
        yMin = data.stream().mapToInt(DataPoint::getY).min().orElse(0);
        yMax = data.stream().mapToInt(DataPoint::getY).max().orElse(0);
        dataSet.clear();
        dataSet.addAll(data);
        invalidate();
    }


    private void configurePaints(){
        dataPointPaint.setColor(Color.BLUE);
        dataPointPaint.setStrokeWidth(7f);
        dataPointPaint.setStyle(Paint.Style.STROKE);

        dataPointFillPaint.setColor(Color.WHITE);

        dataPointLinePaint.setColor(Color.BLUE);
        dataPointLinePaint.setStrokeWidth(7f);
        dataPointLinePaint.setAntiAlias(true);

        axisLinePaint.setColor(Color.RED);
        axisLinePaint.setStrokeWidth(10f);
    }


    private float convertToRealX(int xValue){
        return ((float)xValue / xMax) * getWidth();
    }
    private float convertToRealY(int xValue){
        return ((float)xValue / yMax) * getHeight();
    }

}
