package com.example.dame_from_scratch.model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Objects;

public class Pawn {

    public static Pawn[][] pawnsPosition;
    static {
        initBoard();
    }
    protected Color color;
    protected int row;
    protected int col;
    public static final int NUM_COLS = 10;
    public static final int NUM_ROWS = 10;
    public static final Image WHITE_PAWN_SPRITE = loadImage(Color.WHITE, false);
    public static final Image PURPLE_PAWN_SPRITE = loadImage(Color.PURPLE, false);

    public Pawn(Color color, int rowInit, int colInit) {
        this.color = color;
        this.row = rowInit;
        this.col = colInit;
    }

    public Color getColor() {
        return this.color;
    }
    public void moveTo(int newRow, int newCol) {

        /*
        // If a capture took place, this will be triggered
        if (Math.abs(newRow - this.row) > 1 && Math.abs(newCol - this.col) > 1) {
            System.out.println("Bauer gefangen!" + this.findAllCapturedPawns(newRow, newCol));
            this.findAllCapturedPawns(newRow, newCol).forEach(pawn -> {
                pawn.getCaptured();
            });

        }
*/
        pawnsPosition[this.row][this.col] = null;
        pawnsPosition[newRow][newCol] = this;
        this.row = newRow;
        this.col = newCol;
        System.out.println("pawn moved");
        // Promote pawn to king
        /*
        if (canBePromoted()) {
            this.getPromoted();
        }
        */

    }

    public Image getSprite(Color wantedColor) {
        return wantedColor.equals(Color.WHITE) ? Pawn.WHITE_PAWN_SPRITE : Pawn.PURPLE_PAWN_SPRITE;
    }

    protected static Image loadImage(Color wantedColor, boolean isKing) {
        String imgColor = wantedColor.equals(Color.WHITE) ? "white" : "purple";
        String imgPath = isKing ? "/" + imgColor + "-king.png" : "/" + imgColor + "-pawn.png";
        return new Image(Objects.requireNonNull(Pawn.class.getResource(imgPath)).toExternalForm());
    }

    public static void initBoard() {
        // Initialize the position of white and purple pawns
        pawnsPosition = new Pawn[NUM_ROWS][NUM_COLS];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                if ((i + j) % 2 != 0) {
                    pawnsPosition[i][j] = new Pawn(Color.PURPLE, i, j);
                } else {
                    pawnsPosition[NUM_ROWS - (i + 1)][j] = new Pawn(Color.WHITE, NUM_ROWS - (i + 1), j);
                }

            }
        }
    }
}
