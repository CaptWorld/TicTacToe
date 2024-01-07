package org.capt.world;

import org.capt.world.model.Board;
import org.capt.world.model.player.*;

import java.util.Objects;

public class Main {

    public static int play(Player p1, Player p2) {
        char currentPlayerChar = 'X';

        Board board = new Board();
        board.render();

        Player currentPlayer = p1;

        while (true) {

            while (true) {
                var move = currentPlayer.getNextMove(board, currentPlayerChar);
                System.out.printf("Player-%s [%s] made move on %s\n", currentPlayerChar, currentPlayer.getClass().getSimpleName(), move);
                if (board.isValidMove(move)) {
                    board = board.makeMove(move, currentPlayerChar);
                    break;
                } else System.out.println("Invalid move, try again");
            }

            board.render();

            Character ch = board.getWinner();
            if (Objects.nonNull(ch)) {
                System.out.println("Winner is player-" + ch + " " + currentPlayer.getClass().getSimpleName());
                return currentPlayer == p1 ? 1 : -1;
            }

            if (board.isDraw()) {
                System.out.println("Game is drawn.");
                return 0;
            }

            currentPlayerChar = currentPlayerChar == 'X' ? 'O' : 'X';
            currentPlayer = currentPlayer == p1 ? p2 : p1;
        }
    }

    public static void repeatedBattles(Player p1, Player p2, long nPlays) {
        long p1Wins = 0;
        long nDraws = 0;
        long p2Wins = 0;

        for (long i = 0; i < nPlays; i++) {
            int result = play(p1, p2);
            if (result == 1) {
                p1Wins++;
            } else if (result == 0) {
                nDraws++;
            } else {
                p2Wins++;
            }
        }

        System.out.println("Statistics:-");
        System.out.printf("\tPlayer-1 [%s] wins: %d (%.3f)\n", p1.getClass().getSimpleName(), p1Wins, 1.0 * p1Wins / nPlays);
        System.out.printf("\tPlayer-2 [%s] wins: %d (%.3f)\n", p2.getClass().getSimpleName(), p2Wins, 1.0 * p2Wins / nPlays);
        System.out.printf("\tDraws: %d (%.3f)\n", nDraws, 1.0 * nDraws / nPlays);
    }

    public static void main(String[] args) {

        Player p1 = new Human();
        Player p2 = new WinningAndLosingMoveDetector();

        repeatedBattles(p1, p2, 1);
    }

}