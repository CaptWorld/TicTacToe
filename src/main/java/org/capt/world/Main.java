package org.capt.world;

import org.capt.world.model.Board;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        char currentPlayer = 'X';

        Board board = new Board();
        board.render();

        while (true) {

            while (true) {
                var p = Board.get_move(currentPlayer);
                if (board.isValidMove(p)) {
                    board.makeMove(p, currentPlayer);
                    break;
                } else System.out.println("Invalid move, try again");
            }

            board.render();

            Character ch = board.getWinner();
            if (Objects.nonNull(ch)) {
                System.out.println("Winner is player-" + ch);
                break;
            }

            if (board.isDraw()) {
                System.out.println("Game is drawn.");
                break;
            }

            currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
        }
    }
}