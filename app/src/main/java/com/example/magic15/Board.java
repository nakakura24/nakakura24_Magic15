package com.example.magic15;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Game board class with board properties and game functions.
 *
 * @author Cameron Nakakura
 * @version 9/28/2022
 */
public class Board {

    /* Instance variables */
    private Square[][] board; // the 2d array with the state of the game board
    private int size; // the size of the game board
    private final float squareLength; // the drawing length of a square (fraction of a screen)

    /* Static constants */
    private static final float GAP_SIZE = 0.02f; // the length of space between squares (fraction)

    /**
     * Constructor for Board class.
     *
     * @param size the size of the game board
     */
    public Board(int size) {
        this.size = size;

        /* set square paints */
        Square.unsolvedPaint.setColor(Color.DKGRAY); // set unsolved color to dark gray
        Square.solvedPaint.setColor(0xFF3700B3); // set solved color to android purple
        Square.textPaint.setColor(Color.WHITE); // set text color to white
        Square.textPaint.setTextSize(500f / size); // set text size to proportion with size
        Square.textPaint.setTextAlign(Paint.Align.CENTER); // set text to draw in horizontal center

        /* set array list of numbers from 0 to size squared */
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for (int i = 0 ; i < size * size ; i++) {
            nums.add(i);
        }

        squareLength = (1 - (size + 1) * GAP_SIZE) / size; // set drawing square length

        board = new Square[size][size]; // create new board of size x size
        for (int i = 0 ; i < size ; i++) {
            for (int j = 0 ; j < size ; j++) {
                int randi = (int) (Math.random() * nums.size()); // generate random number
                board[i][j] = new Square(
                        nums.get(randi), // set square number to random from 1 to size squared
                        i, // row
                        j, // column
                        new Rect(
                                (int)(MagicModel.screenWidth * ((1 + j) * GAP_SIZE + j * squareLength)),
                                (int)(MagicModel.screenWidth * ((1 + i) * GAP_SIZE + i * squareLength)),
                                (int)(MagicModel.screenWidth * (1 + j) * (GAP_SIZE + squareLength)),
                                (int)(MagicModel.screenWidth * (1 + i) * (GAP_SIZE + squareLength))
                        ) // set drawing rect based on row, col, and square length
                );
                nums.remove(randi); // remove number from pool; already used
            }
        }
    }

    /**
     * Returns the game board.
     *
     * @return the game board
     */
    public Square[][] getBoard() {return board;}

    /**
     * Returns the size of the game board.
     *
     * @return the size of the game board
     */
    public int getSize() {return size;}

    /**
     * Sets each square on the game board to the correct solved status.
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
     * Swaps the numbers on 2 squares.
     *
     * @param i1 the row of the 1st square
     * @param j1 the column of the 1st square
     * @param i2 the row of the 2nd square
     * @param j2 the column of the 2nd square
     */
    public void swapSquares(int i1, int j1, int i2, int j2) {
        int temp = board[i1][j1].getNum(); // save 1st num
        board[i1][j1].setNum(board[i2][j2].getNum()); // set 1st square to 2nd num
        board[i2][j2].setNum(temp); // set 2nd square to 1st num
    }

    /**
     * Checks if a square is near the empty spot and swaps it if it is.
     *
     * @param i the row of the square to check
     * @param j the column of the square to check
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
     * Draws the game board.
     *
     * @param canvas the canvas to draw on
     */
    public void drawBoard(Canvas canvas) {
        for (int i = 0 ; i < size ; i++) {
            for (int j = 0 ; j < size ; j++) {
                board[i][j].drawSquare(canvas);
            }
        }
    }
}
