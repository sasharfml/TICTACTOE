package tictactoe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NewGameBoard extends JPanel {
    public static final int ROWS = 3;
    public static final int COLS = 3;
    public static final int CELL_SIZE = 120;
    public static final int CANVAS_WIDTH = CELL_SIZE * COLS;
    public static final int CANVAS_HEIGHT = CELL_SIZE * ROWS;
    public static final int GRID_WIDTH = 8;
    public static final int GRID_WIDTH_HALF = GRID_WIDTH / 2;
    public static final Color COLOR_GRID = Color.LIGHT_GRAY;

    private Cell[][] cells;
    private Seed currentPlayer;
    private State currentState;
    private Image backgroundImage;

    public NewGameBoard() {
        initGame();
        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT + 150));

        ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/tictactoe/interface.png"));
        backgroundImage = backgroundIcon.getImage();

        setLayout(null);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();
                int rowSelected = (mouseY - getVerticalOffset()) / CELL_SIZE;
                int colSelected = (mouseX - getHorizontalOffset()) / CELL_SIZE;

                if (currentState == State.PLAYING) {
                    if (rowSelected >= 0 && rowSelected < ROWS && colSelected >= 0 && colSelected < COLS) {
                        if (cells[rowSelected][colSelected].content == Seed.NO_SEED) {
                            cells[rowSelected][colSelected].content = currentPlayer;
                            updateGame(currentPlayer, rowSelected, colSelected);
                            currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
                        }
                    }
                }
                repaint();
            }
        });
    }

    public void initGame() {
        cells = new Cell[ROWS][COLS];
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col] = new Cell(row, col);
            }
        }
        currentPlayer = Seed.CROSS;
        currentState = State.PLAYING;
    }

    public void updateGame(Seed player, int row, int col) {
        if (hasWon(player, row, col)) {
            currentState = (player == Seed.CROSS) ? State.CROSS_WON : State.NOUGHT_WON;
            String winner = (player == Seed.CROSS) ? "Player X" : "Player O";
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(this, "Congratulations! " + winner + " is the Winner!!");
            });
        } else if (isDraw()) {
            currentState = State.DRAW;
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(this, "It's a Draw!");
            });
        }
    }

    public boolean hasWon(Seed player, int row, int col) {
        return (cells[row][0].content == player && cells[row][1].content == player && cells[row][2].content == player)
                || (cells[0][col].content == player && cells[1][col].content == player && cells[2][col].content == player)
                || (row == col && cells[0][0].content == player && cells[1][1].content == player && cells[2][2].content == player)
                || (row + col == 2 && cells[0][2].content == player && cells[1][1].content == player && cells[2][0].content == player);
    }

    public boolean isDraw() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                if (cells[row][col].content == Seed.NO_SEED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        int xOffset = getHorizontalOffset();
        int yOffset = getVerticalOffset();

        g.setColor(COLOR_GRID);
        for (int row = 1; row < ROWS; ++row) {
            g.fillRoundRect(xOffset, yOffset + CELL_SIZE * row - GRID_WIDTH_HALF,
                    CANVAS_WIDTH - 1, GRID_WIDTH,
                    GRID_WIDTH, GRID_WIDTH);
        }
        for (int col = 1; col < COLS; ++col) {
            g.fillRoundRect(xOffset + CELL_SIZE * col - GRID_WIDTH_HALF, yOffset,
                    GRID_WIDTH, CANVAS_HEIGHT - 1,
                    GRID_WIDTH, GRID_WIDTH);
        }

        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col].paint(g, xOffset, yOffset);
            }
        }

        g.setColor(Color.BLACK);
        String turnText = (currentPlayer == Seed.CROSS) ? "X's Turn" : "O's Turn";
        g.drawString(turnText, 10, CANVAS_HEIGHT + 20);
    }

    private int getHorizontalOffset() {
        return (getWidth() - CANVAS_WIDTH) / 2;
    }

    private int getVerticalOffset() {
        return (getHeight() - CANVAS_HEIGHT) / 2 + 28;
    }
}