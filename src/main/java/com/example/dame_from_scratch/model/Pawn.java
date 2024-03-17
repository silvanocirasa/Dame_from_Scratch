package com.example.dame_from_scratch.model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
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

    public List<Move> getValidMoves() {
        List<Move> validMoves = new ArrayList<>();

        // Define the direction of movement based on the pawn's color
        int direction = this.color.equals(Color.BLACK) ? -1 : 1;

        // Check for regular moves and captures in both diagonal directions
        for (int dx = -1; dx <= 1; dx += 2) {
            int newRow = this.row + direction;
            int newCol = this.col + dx;

            // Check if the new position is within the board boundaries
            if (newRow >= 0 && newRow < NUM_ROWS && newCol >= 0 && newCol < NUM_COLS) {
                // Check if the new position is empty for a regular move
                if (pawnsPosition[newRow][newCol] == null) {
                    validMoves.add(new Move(newRow, newCol));
                }
                // Check if the new position has an opponent's pawn and the position beyond it is empty for a capture
                else if (!pawnsPosition[newRow][newCol].getColor().equals(this.color) &&
                        newRow + direction >= 0 && newRow + direction < NUM_ROWS &&
                        newCol + dx >= 0 && newCol + dx < NUM_COLS &&
                        pawnsPosition[newRow + direction][newCol + dx] == null) {
                    validMoves.add(new Move(newRow + direction, newCol + dx));
                }
            }
        }

        return validMoves;
    }
}
