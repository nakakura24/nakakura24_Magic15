package com.example.magic15;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Square {

    private int num, row, col;
    private boolean solved;
    private Rect rect;

    public static final Paint unsolvedPaint = new Paint();
    public static final Paint solvedPaint = new Paint();
    public static final Paint textPaint = new Paint();

    /**
     *
     * @param num
     */
    public Square(int num, int row, int col, Rect rect) {
        this.num = num;
        this.row = row;
        this.col = col;
        solved = false;
        this.rect = rect;
    }

    /**
     *
     */
    public void setSolved(boolean solved) {this.solved = solved;}

    /**
     *
     * @param num
     */
    public void setNum(int num) {this.num = num;}

    /**
     *
     * @return
     */
    public int getNum() {return num;}

    /**
     *
     * @return
     */
    public Rect getRect() {return rect;}

    /**
     *
     * @param canvas
     */
    public void drawSquare(Canvas canvas) {
        if (num != 0) {
            canvas.drawRect(rect, solved ? solvedPaint:unsolvedPaint);
            canvas.drawText(""+num,
                    rect.centerX(),
                    rect.centerY() + (int)(textPaint.getTextSize() / 3),
                    textPaint
            );
        }
    }
}
