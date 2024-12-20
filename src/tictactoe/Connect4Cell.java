package tictactoe;

import java.awt.Graphics;

/**
 * The Cell class models each individual cell of the game board.
 */
public class Connect4Cell {
    public static final int SIZE = 70; // Adjusted to match CELL_SIZE in Connect4Board
    public static final int PADDING = SIZE / 5;
    public static final int SEED_SIZE = SIZE - PADDING * 2;

    Seed content;
    int row, col;

    public Connect4Cell(int row, int col) {
        this.row = row;
        this.col = col;
        content = Seed.NO_SEED;
    }

    public void newGame() {
        content = Seed.NO_SEED;
    }

    public void paint(Graphics g, int xOffset, int yOffset) {
        int x1 = xOffset + col * SIZE + PADDING;
        int y1 = yOffset + row * SIZE + PADDING;
        if (content == Seed.CROSS || content == Seed.NOUGHT) {
            g.drawImage(content.getImage(), x1, y1, SEED_SIZE, SEED_SIZE, null);
        }
    }
}