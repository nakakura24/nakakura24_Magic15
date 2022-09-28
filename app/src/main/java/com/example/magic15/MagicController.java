package com.example.magic15;

import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;

public class MagicController implements
        View.OnClickListener,
        View.OnTouchListener,
        SeekBar.OnSeekBarChangeListener {

    private MagicView magicView;
    private MagicModel magicModel;

    /**
     *
     * @param magicView
     */
    public MagicController(MagicView magicView) {
        this.magicView = magicView;
        this.magicModel = magicView.getMagicModel();
    }

    @Override
    public void onClick(View view) {
        magicModel.magicBoard = new Board(magicModel.progressSize);
        magicModel.magicBoard.setSolved();
        magicView.invalidate();
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        Square[][] board = magicModel.magicBoard.getBoard();
        int size = magicModel.magicBoard.getSize();

        outer:
        for (int i = 0 ; i < size ; i++) {
            for (int j = 0 ; j < size ; j++) {
                if (board[i][j].getRect().contains((int) x, (int) y)) {
                    magicModel.magicBoard.swapCheck(i, j);
                    break outer;
                }
            }
        }
        magicModel.magicBoard.setSolved();
        magicView.invalidate();
        return true;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        magicModel.progressSize = progress + 3;
    }



    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}
}
