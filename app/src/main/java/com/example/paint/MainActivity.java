package com.example.paint;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private PaintView p;
    private int i = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        p = new PaintView(this);
        ConstraintLayout v = findViewById(R.id.content);
        v.addView(p);
    }

    public void btnUndo(View view){
        p.Undo();
    }

    public void btnStrokeWidth(View view) {
        MaterialButton btn = findViewById(R.id.btnResize);
        i *= 2;
        p.setStrokeWidth(p.w * i);

        int heightValue = 22;
        heightValue += p.w * i;
        btn.setIconSize(heightValue);

        if(i > 8){
            i = 1;
        }
    }
}