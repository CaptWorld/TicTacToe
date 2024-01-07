package org.capt.world.model.player;

import org.capt.world.model.Board;
import org.capt.world.model.Point;

import java.util.List;
import java.util.Objects;

public class WinningAndLosingMoveDetector implements Player {
    @Override
    public Point getNextMove(Board board, char currentPlayer) {
        List<Point> legalMoves = board.getLegalMoves();
        char oppositionPlayer = currentPlayer == 'X' ? 'O' : 'X';
        Point moveToPreventLoss = null;
        for (Point legalMove : legalMoves) {
            Board b = board.makeMove(legalMove, currentPlayer);
            Character winner = b.getWinner();
            if (Objects.nonNull(winner)) {
                if (winner == currentPlayer) {
                    return legalMove;
                }
            } else if (Objects.nonNull(board.makeMove(legalMove, oppositionPlayer).getWinner())) {
                moveToPreventLoss = legalMove;
            }
        }
        if (Objects.nonNull(moveToPreventLoss)) return moveToPreventLoss;
        else return legalMoves.get((int) (Math.random() * legalMoves.size()));
    }
}
