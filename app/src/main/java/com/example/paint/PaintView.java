package com.example.paint;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.Stack;

public class PaintView extends View {

    public ViewGroup.LayoutParams params;
    private Path path = new Path();
    private Paint brush = new Paint();
    private Stack<Path> paths = new Stack<Path>();
    private  Stack<Paint> brushes = new Stack<Paint>();
    public float w = 4f;
    private float currentWidth = 8f;

    public PaintView(Context context) {
        super(context);
        createPencil();
        params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float pointX = event.getX();
        float pointY = event.getY();

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                createPencil();
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
        brushes.push(brush);
    }
    public void Undo(){
        if(!brushes.empty()){
            brushes.pop();
            paths.pop();
        }
        postInvalidate();
    }
    public void createPencil(){
        path = new Path();
        paths.push(path);
        createNewBrush(true, Color.MAGENTA, Paint.Style.STROKE, Paint.Join.ROUND, currentWidth);
    }

    public void setStrokeWidth(float width){
        currentWidth = width;
    }
}
