package com.example.magic15;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class MagicView extends SurfaceView {

    private MagicModel magicModel;

    /**
     * Constructor for MagicView class.
     *
     * @param context
     * @param attrs
     */
    public MagicView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setWillNotDraw(false);

        magicModel = new MagicModel(context);
        magicModel.setMagicBoard(new Board(4));
        magicModel.magicBoard.setSolved();
    }

    /**
     *
     * @return
     */
    public MagicModel getMagicModel() {return magicModel;}

    /**
     *
     * @param canvas
     */
    public void onDraw(Canvas canvas) {
        magicModel.magicBoard.drawBoard(canvas);
    }
}
