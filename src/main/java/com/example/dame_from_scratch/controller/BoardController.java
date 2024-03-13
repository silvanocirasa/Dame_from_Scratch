package com.example.dame_from_scratch.controller;

import com.example.dame_from_scratch.model.Pawn;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BoardController {

    private static Pawn currentlySelectedPawn = null;


    public void updateCellReferential(int i, int j) {
        Pawn pawn = Pawn.pawnsPosition[i][j];
        if (pawn != null) {

            /*if (!pawn.getColor().equals(currentGame.getWhoIsPlaying().getPlayingColor()))
                return;*/

            currentlySelectedPawn = pawn;
            System.out.println("Bauer angeklickt!");
            //List<Cell> cellsToHighlight = pawn.getNextCells();
            //cellsToHighlight.stream().forEach(cell -> Cell.highlightedCells[cell.getRow()][cell.getCol()]= true);
        }
    }

    public void getInput(GridPane board, Stage primaryStage) {

        for (int i = 0; i < Pawn.NUM_ROWS; i++) {
            for (int j = 0; j < Pawn.NUM_COLS; j++) {
                    StackPane cell = new StackPane();
                    final AtomicInteger row = new AtomicInteger(i);
                    final AtomicInteger col = new AtomicInteger(j);
                    cell.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
                        currentlySelectedPawn.moveTo(row.get(), col.get());
                        renderBoard(board, false, primaryStage);
                        currentlySelectedPawn = null;
                    });
                }
            }
        }




    public void renderBoard(GridPane board, boolean firstInit, Stage primaryStage) {
        for (int i = 0; i < Pawn.NUM_ROWS; i++) {
            for (int j = 0; j < Pawn.NUM_COLS; j++) {
                StackPane cell = new StackPane();
                final AtomicInteger row = new AtomicInteger(i);
                final AtomicInteger call= new AtomicInteger(j);
                cell.addEventFilter(MouseEvent.MOUSE_CLICKED, e-> {
                   /* if (!currentGame.hasStarted())
                        return;*/
                    updateCellReferential(row.get(),call.get());
                    getInput(board, primaryStage); // show valid cells later
                });
                Pawn pawn = Pawn.pawnsPosition[i][j];
                if (pawn != null) {
                    ImageView pawnView = new ImageView(pawn.getSprite(pawn.getColor()));
                    pawnView.setFitWidth(55);
                    pawnView.setPreserveRatio(true);
                    pawnView.setSmooth(true);
                    pawnView.setCache(true);
                    cell.getChildren().add(pawnView);
                }
                if ((i + j) % 2 == 0) {
                    cell.setStyle("-fx-background-color: #FFCE9E");
                } else {
                    cell.setStyle("-fx-background-color: #D18B47");
                }
                Node nodeToModify = getNodeByCoordinate(j, i, board);

                if (nodeToModify == null) {
                    board.add(cell, j, i);
                } else if (nodeToModify instanceof StackPane){
                    if (!firstInit)
                        board.getChildren().remove(nodeToModify);
                    board.add(cell, j, i);
                }
            }
        }
    }

    Node getNodeByCoordinate(Integer row, Integer column, GridPane board) {
        for (Node node : board.getChildren()) {
            if(GridPane.getColumnIndex(node) == row && GridPane.getColumnIndex(node) == column){
                return node;
            }
        }
        return null;
    }


}
