package com.example.magic15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;

/**
 * Main activity class that starts a game of Magic 15.
 *
 * @author Cameron Nakakura
 * @version 9/28/2022
 *
 * Enhancements: Adjustable size (restart game after setting desired size (3 to 9) using seek bar)
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        /* MagicView and MagicController */
        MagicView magicView = findViewById(R.id.magicView);
        MagicController magicController = new MagicController(magicView);

        /* Touch controls */
        magicView.setOnTouchListener(magicController);

        /* Restart button controls */
        Button restartButton = findViewById(R.id.restartButton);
        restartButton.setOnClickListener(magicController);

        /* Resize game controls */
        SeekBar sizeBar = findViewById(R.id.sizeBar);
        sizeBar.setOnSeekBarChangeListener(magicController);
    }
}