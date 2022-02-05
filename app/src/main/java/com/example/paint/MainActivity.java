package com.example.paint;

import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.shapes.Shape;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

public class MainActivity extends AppCompatActivity {

    private PaintView p;
    private int i = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        p = new PaintView(this);
        ConstraintLayout v = findViewById(R.id.content);
        v.addView(p);


    }

    public void sendMessage(View view) {
        i++;
        p.setStrokeWidth(p.w * i);

        if(i > 3){
            i = 0;
        }
    }
}