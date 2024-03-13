package com.example.dame_from_scratch.view;

import com.example.dame_from_scratch.controller.BoardController;
import com.example.dame_from_scratch.model.Pawn;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class BoardView {

    private static BoardController boardController = new BoardController();
    public static void initBoard(BorderPane root, Stage primaryStage){


        GridPane board = new GridPane();
        buildGrid(board);
        initGrid(board, primaryStage);

        root.setCenter(board);
    }

    public static void buildGrid(GridPane board) {
        board.setGridLinesVisible(false);

        for (int i = 0; i < Pawn.NUM_COLS; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100 / Pawn.NUM_COLS);
            board.getColumnConstraints().add(colConst);
        }

        for (int i = 0; i < Pawn.NUM_COLS; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100 / Pawn.NUM_COLS);
            board.getRowConstraints().add(rowConst);
        }
        board.setMaxSize(600, 600);
        board.setGridLinesVisible(true);
    }

    public static void initGrid(GridPane board,Stage primaryStage) {
        boardController.renderBoard(board, true, primaryStage);
    }
}

