package com.example.magic15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        MagicView magicView = findViewById(R.id.magicView);
        MagicController magicController = new MagicController(magicView);

        magicView.setOnTouchListener(magicController);

        Button restartButton = findViewById(R.id.restartButton);
        restartButton.setOnClickListener(magicController);

        SeekBar sizeBar = findViewById(R.id.sizeBar);
        sizeBar.setOnSeekBarChangeListener(magicController);
    }
}