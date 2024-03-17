package com.example.dame_from_scratch.model;

import java.util.*;

public class Game {
    private Player whoIsPlaying;

    public Game() {
        this.whoIsPlaying = whoIsPlaying;
    }

    public void moveAI() {
        // 1. Identify all the AI's pawns
        List<Pawn> aiPawns = new ArrayList<>();
        for (int i = 0; i < Pawn.NUM_ROWS; i++) {
            for (int j = 0; j < Pawn.NUM_COLS; j++) {
                Pawn pawn = Pawn.pawnsPosition[i][j];
                if (pawn != null && pawn.getColor().equals("black")) {
                    aiPawns.add(pawn);
                }
            }
        }
        System.out.println("Initial game board: " + this.toString());
        // 2. For each pawn, identify all the valid moves it can make
        Map<Pawn, List<Move>> validMoves = new HashMap<>();
        for (Pawn pawn : aiPawns) {
            List<Move> moves = pawn.getValidMoves();
            System.out.println("Possible moves for pawn " + pawn + ": " + moves);
            if (!moves.isEmpty()) {
                validMoves.put(pawn, moves);
            }
        }

        System.out.println("AI valid moves: " + validMoves);

        // 3. Randomly select one of the pawns and one of its valid moves
        if (!validMoves.isEmpty()) {
            List<Pawn> pawns = new ArrayList<>(validMoves.keySet());
            Pawn selectedPawn = pawns.get(new Random().nextInt(pawns.size()));
            List<Move> moves = validMoves.get(selectedPawn);
            Move selectedMove = moves.get(new Random().nextInt(moves.size()));

            // 4. Update the game state with the selected move
            selectedPawn.moveTo(selectedMove.getRow(), selectedMove.getCol());
        }

        System.out.println("AI moved");
    }

    private Object getWhoIsPlaying() {
        return null;
    }
}
