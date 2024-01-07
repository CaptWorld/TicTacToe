package org.capt.world.model.player;

import org.capt.world.model.Board;
import org.capt.world.model.Point;

public interface Player {
    Point getNextMove(Board board, char currentPlayer);
}
