package org.capt.world.model;

import java.util.Objects;

public class Board {

    private static final short BOARD_SIZE = 3;

    private final Character[][] state;

    public Board() {
        state = new Character[BOARD_SIZE][BOARD_SIZE];
    }

    public void render() {
        System.out.println();
        System.out.print("  ");
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.print("  ");
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print("--");
        }
        System.out.println();
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print(i + "|");
            for (int j = 0; j < BOARD_SIZE; j++) {
                Character ch = state[i][j];
                System.out.print(Objects.isNull(ch) ? ' ' : ch);
                if (j != BOARD_SIZE - 1) {
                    System.out.print(' ');
                }
            }
            System.out.println('|');
        }
        System.out.print("  ");
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print("--");
        }
        System.out.println();
        System.out.println();
    }
}
