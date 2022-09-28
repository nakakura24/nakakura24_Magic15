package com.example.magic15;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

public class Board {

    //private ArrayList<Square> squares;
    private Square[][] board;
    private int size;
    private final float squareLength;

    private static final float GAP_SIZE = 0.02f;

    /**
     *
     * @param size
     */
    public Board(int size) {
        this.size = size;

        Square.unsolvedPaint.setColor(Color.DKGRAY);
        Square.solvedPaint.setColor(0xFF3700B3);
        Square.textPaint.setColor(Color.WHITE);
        Square.textPaint.setTextSize(500f / size);
        Square.textPaint.setTextAlign(Paint.Align.CENTER);

        ArrayList<Integer> nums = new ArrayList<Integer>();
        for (int i = 0 ; i < size * size ; i++) {
            nums.add(i);
        }

        squareLength = (1 - (size + 1) * GAP_SIZE) / size;

        board = new Square[size][size];
        for (int i = 0 ; i < size ; i++) {
            for (int j = 0 ; j < size ; j++) {
                int randi = (int) (Math.random() * nums.size());
                board[i][j] = new Square(
                        nums.get(randi),
                        i,
                        j,
                        new Rect(
                                (int)(MagicModel.screenWidth * ((1 + j) * GAP_SIZE + j * squareLength)),
                                (int)(MagicModel.screenWidth * ((1 + i) * GAP_SIZE + i * squareLength)),
                                (int)(MagicModel.screenWidth * (1 + j) * (GAP_SIZE + squareLength)),
                                (int)(MagicModel.screenWidth * (1 + i) * (GAP_SIZE + squareLength))
                        )
                );
                nums.remove(randi);
            }
        }
    }

    /**
     *
     * @return
     */
    public Square[][] getBoard() {return board;}

    /**
     *
     * @return
     */
    public int getSize() {return size;}

    /**
     *
     */
    public void setSolved() {
        for (int i = 0 ; i < size ; i++) {
            for (int j = 0 ; j < size ; j++) {
                board[i][j].setSolved(
                        board[i][j].getNum() == (size * i + j + 1) % (size * size)
                );
            }
        }
    }

    /**
     *
     * @param i1
     * @param j1
     * @param i2
     * @param j2
     */
    public void swapSquares(int i1, int j1, int i2, int j2) {
        int temp = board[i1][j1].getNum();
        board[i1][j1].setNum(board[i2][j2].getNum());
        board[i2][j2].setNum(temp);
    }

    /**
     *
     * @param i
     * @param j
     */
    public void swapCheck(int i, int j) {
        /* check above */
        if (i > 0 && board[i - 1][j].getNum() == 0) {
            swapSquares(i, j, i - 1, j);
        }

        /* check below */
        else if (i < size - 1 && board[i + 1][j].getNum() == 0) {
            swapSquares(i, j, i + 1, j);
        }

        /* check left */
        else if (j > 0 && board[i][j - 1].getNum() == 0) {
            swapSquares(i, j, i, j - 1);
        }

        /* check right */
        else if (j < size - 1 && board[i][j + 1].getNum() == 0) {
            swapSquares(i, j, i, j + 1);
        }
    }

    /**
     *
     * @param canvas
     */
    public void drawBoard(Canvas canvas) {
        for (int i = 0 ; i < size ; i++) {
            for (int j = 0 ; j < size ; j++) {
                board[i][j].drawSquare(canvas);
            }
        }
    }
}
