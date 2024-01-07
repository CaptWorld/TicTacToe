package org.capt.world.model;

import java.util.*;
import java.util.stream.Collectors;

public class Board {

    private record Line(List<Point> points) {
        public Character getWinner(Board board) {
            var players = uniquePlayers(board);
            if (players.size() == 1) {
                return players.contains(null) ? null : players.stream().findAny().get();
            } else return null;
        }

        public boolean isDraw(Board board) {
            var players = uniquePlayers(board);
            return players.size() > 1 && !players.contains(null);
        }

        private Set<Character> uniquePlayers(Board board) {
            return points.stream().map(board::getPlayerAt).collect(Collectors.toSet());
        }
    }

    private static final short BOARD_SIZE = 3;
    private static final List<Line> LINES_TO_CHECK_FOR_WINNER;

    static {
        LINES_TO_CHECK_FOR_WINNER = new LinkedList<>();

        List<Point> leftToRightDiagonalPoints = new LinkedList<>();
        List<Point> rightToLeftDiagonalPoints = new LinkedList<>();
        for (short i = 0; i < BOARD_SIZE; i++) {
            leftToRightDiagonalPoints.add(new Point(i, i));
            rightToLeftDiagonalPoints.add(new Point(i, (short) (BOARD_SIZE - 1 - i)));

            List<Point> rowPoints = new LinkedList<>();
            List<Point> colPoints = new LinkedList<>();
            for (short j = 0; j < BOARD_SIZE; j++) {
                rowPoints.add(new Point(i, j));
                colPoints.add(new Point(j, i));
            }
            LINES_TO_CHECK_FOR_WINNER.add(new Line(rowPoints));
            LINES_TO_CHECK_FOR_WINNER.add(new Line(colPoints));
        }
        LINES_TO_CHECK_FOR_WINNER.add(new Line(leftToRightDiagonalPoints));
        LINES_TO_CHECK_FOR_WINNER.add(new Line(rightToLeftDiagonalPoints));
    }

    private final Character[][] state;

    public Board() {
        state = new Character[BOARD_SIZE][BOARD_SIZE];
    }

    public Board(Board board) {
        this();
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.arraycopy(board.state[i], 0, this.state[i], 0, BOARD_SIZE);
        }
    }

    public Board makeMove(Point p, char ch) {
        if (!isValidMove(p)) {
            throw new IllegalArgumentException();
        }
        Board newBoard = new Board(this);
        newBoard.state[p.x()][p.y()] = ch;
        return newBoard;
    }

    public boolean isValidMove(Point p) {
        if (p.x() < 0 || p.x() >= BOARD_SIZE) return false;
        if (p.y() < 0 || p.y() >= BOARD_SIZE) return false;
        return Objects.isNull(getPlayerAt(p));
    }

    private Character getPlayerAt(Point p) {
        return state[p.x()][p.y()];
    }

    public Character getWinner() {
        for (Line line : LINES_TO_CHECK_FOR_WINNER) {
            Character ch = line.getWinner(this);
            if (Objects.nonNull(ch)) {
                return ch;
            }
        }
        return null;
    }

    public boolean isDraw() {
        return LINES_TO_CHECK_FOR_WINNER.stream().allMatch(line -> line.isDraw(this));
    }

    public List<Point> getLegalMoves() {
        List<Point> legalMoves = new ArrayList<>();
        for (short i = 0; i < BOARD_SIZE; i++) {
            for (short j = 0; j < BOARD_SIZE; j++) {
                if (Objects.isNull(state[i][j])) {
                    legalMoves.add(new Point(i, j));
                }
            }
        }
        return legalMoves;
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
