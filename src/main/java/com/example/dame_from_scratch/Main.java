package com.example.dame_from_scratch;

import com.example.dame_from_scratch.view.BoardView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        BorderPane gameRoot = new BorderPane();
        BoardView.initBoard(gameRoot, stage);

        Scene scene = new Scene(gameRoot, 800, 800);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}