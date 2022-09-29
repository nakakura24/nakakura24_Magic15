package com.example.magic15;

import android.content.Context;

/**
 * Model class for current game board and other measurements.
 *
 * @author Cameron Nakakura
 * @version 9/28/2022
 */
public class MagicModel {

    /* Instance variables */
    public Board magicBoard; // current game board
    public int progressSize; // requested board size from progress bar

    /* Static variables */
    public static int screenWidth; // width of device screen

    /**
     * Constructor for MagicModel class.
     *
     * @param context current state of the application
     */
    public MagicModel(Context context) {
        screenWidth = context.getResources().getDisplayMetrics().widthPixels; // get width
        progressSize = 4; // default 4x4
    }

    /**
     * Sets the current game board.
     *
     * @param board the board to change the game board to
     */
    public void setMagicBoard(Board board) {magicBoard = board;}
}
