package com.example.magic15;

import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;

/**
 * Controller class that registers user input controls.
 *
 * @author Cameron Nakakura
 * @version 9/28/2022
 */
public class MagicController implements
        View.OnClickListener,
        View.OnTouchListener,
        SeekBar.OnSeekBarChangeListener {

    /* Instance variables */
    private MagicView magicView; // game view to control
    private MagicModel magicModel; // game model

    /**
     * Constructor for MagicController class.
     *
     * @param magicView gave view to be controlled
     */
    public MagicController(MagicView magicView) {
        this.magicView = magicView;
        this.magicModel = magicView.getMagicModel(); // set model to be same model as view
    }

    /**
     * Actions to take when a button is clicked.
     *
     * @param view the view that was clicked
     */
    @Override
    public void onClick(View view) {
        /* Restart game with new board */
        magicModel.magicBoard = new Board(magicModel.progressSize); /* set game board to new board
                                                                       with requested size */
        magicModel.magicBoard.setSolved(); // set each square to the correct color
        magicView.invalidate();
    }

    /**
     * Actions to take when the screen is touched.
     *
     * @param view the view that was touched
     * @param event the touch information
     * @return to not search below the touch
     */
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        /* Variables */
        float x = event.getX(); // horizontal location of touch
        float y = event.getY(); // vertical location of touch
        Square[][] board = magicModel.magicBoard.getBoard(); // current game board
        int size = magicModel.magicBoard.getSize(); // size of current game board

        outer:
        for (int i = 0 ; i < size ; i++) {
            for (int j = 0 ; j < size ; j++) {
                /* if square was clicked */
                if (board[i][j].getRect().contains((int) x, (int) y)) {
                    magicModel.magicBoard.swapCheck(i, j); // swap with empty square if adjacent
                    break outer; // done searching for clicked square
                }
            }
        }
        magicModel.magicBoard.setSolved(); // set each square to the correct color after change
        magicView.invalidate();
        return true;

        /*
        External Citation:
            Date: 9/28/2022
            Problem: Could not find a straight-forward way to tell if a square was pressed.
            Resource:
                https://developer.android.com/reference/android/graphics/Rect
            Solution: I found a function contains() which can easily tell if the coordinates of a
                      touch were inside the bounds of square.
        */
    }

    /**
     * Actions to take when the seek bar is changed.
     *
     * @param seekBar the seek bar that was changed
     * @param progress the value of the changed seek bar
     * @param b if seek bar was changed by the user
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        magicModel.progressSize = progress + 3; // seek bar range 0 to 7; size range 3 to 10
    }

    /** Unused interface functions **/
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}
}
