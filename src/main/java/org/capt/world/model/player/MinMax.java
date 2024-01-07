package org.capt.world.model.player;

import org.capt.world.model.Board;
import org.capt.world.model.Point;

import java.util.*;
import java.util.stream.IntStream;

public class MinMax implements Player {

    @Override
    public Point getNextMove(Board board, char currentPlayer) {
        List<Point> legalMoves = board.getLegalMoves();

        record MoveScore(Point move, Integer score) {}

        return legalMoves.stream().map(move -> {
            Board b = board.makeMove(move, currentPlayer);
            return new MoveScore(move, minMaxScore(b,  currentPlayer == 'X' ? 'O' : 'X', currentPlayer));
        }).max(Comparator.comparing(ms -> ms.score)).orElseThrow().move();
    }

    private int minMaxScore(Board board, char player, char currentPlayer) {
        Character winner = board.getWinner();
        if (Objects.nonNull(winner)) {
            return 10 * (winner == currentPlayer ? 1 : -1);
        } else if (board.isDraw()) {
            return 0;
        }

        char nextPlayerChar = player == 'X' ? 'O' : 'X';
        IntStream scoresStream = board.getLegalMoves().stream().map(move -> board.makeMove(move, player)).mapToInt(b -> minMaxScore(b, nextPlayerChar, currentPlayer));

        if (player == currentPlayer) {
            return scoresStream.max().orElseThrow();
        } else {
            return scoresStream.min().orElseThrow();
        }
    }
}
