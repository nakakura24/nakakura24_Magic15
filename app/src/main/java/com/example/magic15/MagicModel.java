package com.example.magic15;


import android.content.Context;

public class MagicModel {

    public Board magicBoard;
    public int progressSize;

    public static int screenWidth;

    public MagicModel(Context context) {
        screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        progressSize = 4;
    }

    public void setMagicBoard(Board board) {
        magicBoard = board;
    }
}
