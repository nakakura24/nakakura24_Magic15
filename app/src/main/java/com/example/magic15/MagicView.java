package com.example.magic15;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * Surface view extension class that draws the game board.
 *
 * @author Cameron Nakakura
 * @version 9/28/2022
 */
public class MagicView extends SurfaceView {

    /* instance variables */
    private MagicModel magicModel; // game model

    /**
     * Constructor for MagicView class.
     *
     * @param context current state of the application
     * @param attrs a collection of attributes
     */
    public MagicView(Context context, AttributeSet attrs) {
        /* REQUIRED */
        super(context, attrs);
        setWillNotDraw(false);

        /* Create game of Magic 15 */
        magicModel = new MagicModel(context); // create model
        magicModel.setMagicBoard(new Board(4)); // set current game board to new 4x4 game
        magicModel.magicBoard.setSolved(); // set each square to the correct color
    }

    /**
     * Returns the game model.
     *
     * @return the game model.
     */
    public MagicModel getMagicModel() {return magicModel;}

    /**
     * Draws the game board.
     *
     * @param canvas the canvas to draw on
     */
    public void onDraw(Canvas canvas) {
        magicModel.magicBoard.drawBoard(canvas);
    }
}
