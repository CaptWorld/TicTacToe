package org.capt.world.model.player;

import org.capt.world.model.Board;
import org.capt.world.model.Point;

import java.util.Scanner;

public class Human implements Player {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public Point getNextMove(Board board, char currentPlayer) {
        System.out.printf("What is player-%s move's X co-ordinate?: ", currentPlayer);
        short x = scanner.nextShort();
        System.out.printf("What is player-%s move's Y co-ordinate?: ", currentPlayer);
        short y = scanner.nextShort();
        return new Point(x, y);
    }
}
