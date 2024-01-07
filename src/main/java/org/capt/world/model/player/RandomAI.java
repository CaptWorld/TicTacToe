package org.capt.world.model.player;

import org.capt.world.model.Board;
import org.capt.world.model.Point;

public class RandomAI implements Player {
    @Override
    public Point getNextMove(Board board, char currentPlayer) {
        var legalMoves = board.getLegalMoves();
        return legalMoves.get((int) (Math.random() * legalMoves.size()));
    }
}
