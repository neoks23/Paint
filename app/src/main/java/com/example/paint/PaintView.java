package com.example.paint;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class PaintView extends View {

    public ViewGroup.LayoutParams params;
    private Path path = new Path();
    private Paint brush = new Paint();
    private ArrayList<Path> paths = new ArrayList<Path>();
    private ArrayList<Paint> brushes = new ArrayList<Paint>();
    public float w = 8f;

    public PaintView(Context context) {
        super(context);
        paths.add(path);
        createNewBrush(true, Color.MAGENTA, Paint.Style.STROKE, Paint.Join.ROUND, 8f);

        params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float pointX = event.getX();
        float pointY = event.getY();

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(pointX, pointY);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(pointX, pointY);
                break;
            default:
                return false;
        }

        postInvalidate();
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(int i = 0; i < brushes.size(); i++){
            canvas.drawPath(paths.get(i), brushes.get(i));
        }
    }
    private void createNewBrush(boolean antiAlias, int color, Paint.Style style, Paint.Join join, float width){
        brush = new Paint();
        brush.setAntiAlias(antiAlias);
        brush.setColor(color);
        brush.setStyle(style);
        brush.setStrokeJoin(join);
        brush.setStrokeWidth(width);
        brushes.add(brush);
    }

    public void setStrokeWidth(float width){
        path = new Path();
        paths.add(path);
        createNewBrush(true, Color.MAGENTA, Paint.Style.STROKE, Paint.Join.ROUND, width);

    }
}
