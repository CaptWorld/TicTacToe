package org.capt.world.model.player;

import org.capt.world.model.Board;
import org.capt.world.model.Point;

import java.util.List;
import java.util.Objects;

public class WinningMoveDetector implements Player{
    @Override
    public Point getNextMove(Board board, char currentPlayer) {
        List<Point> legalMoves = board.getLegalMoves();

        for (Point legalMove : legalMoves) {
            Board b = board.makeMove(legalMove, currentPlayer);
            Character winner = b.getWinner();
            if (Objects.nonNull(winner) && winner == currentPlayer) {
                return legalMove;
            }
        }
        return legalMoves.get((int) (Math.random() * legalMoves.size()));
    }
}
