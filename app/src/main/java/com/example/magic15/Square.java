package com.example.magic15;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Square object class with square properties.
 *
 * @author Cameron Nakakura
 * @version 9/28/2022
 */
public class Square {

    /* Instance variables */
    private int num, row, col; // the square's number and location on the board
    private boolean solved; // if the square is in the right place
    private Rect rect; // the drawing location of the square

    /* Static variables (initialized in Board constructor) */
    public static final Paint unsolvedPaint = new Paint();
    public static final Paint solvedPaint = new Paint();
    public static final Paint textPaint = new Paint();

    /**
     * Constructor for Square class.
     *
     * @param num the number of the square
     * @param row the square's row on the board
     * @param col the square's column on the board
     * @param rect the drawing location of the square
     */
    public Square(int num, int row, int col, Rect rect) {
        this.num = num;
        this.row = row;
        this.col = col;
        solved = false; // square is not in the right place by default
        this.rect = rect;
    }

    /**
     * Sets if the square is solved or not.
     *
     * @param solved the solved status to be changed to
     */
    public void setSolved(boolean solved) {this.solved = solved;}

    /**
     * Sets the number of the square.
     *
     * @param num the number to change the square to
     */
    public void setNum(int num) {this.num = num;}

    /**
     * Returns the number of the square.
     *
     * @return the number of the square
     */
    public int getNum() {return num;}

    /**
     * Returns the drawing location of the square.
     *
     * @return the square's Rect variable
     */
    public Rect getRect() {return rect;}

    /**
     * Draws a square.
     *
     * @param canvas the canvas to draw on
     */
    public void drawSquare(Canvas canvas) {
        /* draw square only if it is 1 to 15 */
        if (num != 0) {
            canvas.drawRect(rect, solved ? solvedPaint:unsolvedPaint); // draw square
            canvas.drawText(""+num,
                    rect.centerX(),
                    rect.centerY() + (int)(textPaint.getTextSize() / 3),
                    textPaint
            ); // draw the square's number in the square
        }
        /*
        External Citation:
            Date: 9/28/2022
            Problem: Could not vertically center canvas text.
            Resource:
                https://stackoverflow.com/questions/4909367/how-to-align-text-vertically
            Solution: I learned that it was overly complicated to vertically center the
                      numbers, so I gave up and hardcoded it.
         */
    }
}
