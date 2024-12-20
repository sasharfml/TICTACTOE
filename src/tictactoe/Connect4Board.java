package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Connect4Board extends JPanel {
    public static final int ROWS = 6;
    public static final int COLS = 7;
    public static final int CELL_SIZE = 70;
    public static final int CANVAS_WIDTH = CELL_SIZE * COLS;
    public static final int CANVAS_HEIGHT = CELL_SIZE * ROWS;
    public static final Color COLOR_GRID = Color.LIGHT_GRAY;

    private Connect4Cell[][] cells;
    private Seed currentPlayer;
    private State currentState;
    private int player1Score = 0;
    private int player2Score = 0;
    private int previewCol = -1; // Column index for preview, -1 means no preview

    public Connect4Board() {
        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        setLayout(null);
        initGame();

        Seed.CROSS.toggleImageDua();
        Seed.NOUGHT.toggleImageDua();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int colSelected = mouseX / CELL_SIZE;

                if (currentState == State.PLAYING && colSelected >= 0 && colSelected < COLS) {
                    for (int row = ROWS - 1; row >= 0; row--) {
                        if (cells[row][colSelected].content == Seed.NO_SEED) {
                            cells[row][colSelected].content = currentPlayer;
                            updateGame(currentPlayer, row, colSelected);
                            currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
                            repaint();
                            break;
                        }
                    }
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int mouseX = e.getX();
                int colSelected = mouseX / CELL_SIZE;

                if (colSelected >= 0 && colSelected < COLS) {
                    previewCol = colSelected;
                } else {
                    previewCol = -1;
                }
                repaint();
            }
        });
    }

    private void initGame() {
        cells = new Connect4Cell[ROWS][COLS];
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col] = new Connect4Cell(row, col);
            }
        }
        currentPlayer = Seed.CROSS;
        currentState = State.PLAYING;
    }

    public void resetGame() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col].content = Seed.NO_SEED; // Reset each cell
            }
        }
        currentPlayer = Seed.CROSS; // Reset to starting player
        currentState = State.PLAYING; // Reset game state
        repaint(); // Refresh the board
    }

    public void updateGame(Seed player, int row, int col) {
        if (hasWon(player, row, col)) {
            currentState = (player == Seed.CROSS) ? State.CROSS_WON : State.NOUGHT_WON;
            String winner = (player == Seed.CROSS) ? "Player X" : "Player O";

            if (player == Seed.CROSS) {
                player1Score++;
            } else {
                player2Score++;
            }

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
        return checkDirection(player, row, col, 1, 0) || // Horizontal
                checkDirection(player, row, col, 0, 1) || // Vertical
                checkDirection(player, row, col, 1, 1) || // Diagonal /
                checkDirection(player, row, col, 1, -1);  // Diagonal \
    }

    private boolean checkDirection(Seed player, int row, int col, int deltaRow, int deltaCol) {
        int count = 0;
        for (int i = -3; i <= 3; i++) {
            int r = row + i * deltaRow;
            int c = col + i * deltaCol;
            if (r >= 0 && r < ROWS && c >= 0 && c < COLS && cells[r][c].content == player) {
                count++;
                if (count == 4) return true;
            } else {
                count = 0;
            }
        }
        return false;
    }

    public boolean isDraw() {
        for (int col = 0; col < COLS; ++col) {
            if (cells[0][col].content == Seed.NO_SEED) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(COLOR_GRID);
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                g.drawRect(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                cells[row][col].paint(g, 0, 0);
            }
        }

        // Draw the preview square
        if (previewCol != -1 && currentState == State.PLAYING) {
            for (int row = ROWS - 1; row >= 0; row--) {
                if (cells[row][previewCol].content == Seed.NO_SEED) {
                    // Set color based on the current player
                    if (currentPlayer == Seed.CROSS) {
                        g.setColor(new Color(0, 255, 0, 50)); // Semi-transparent green for player X
                    } else if (currentPlayer == Seed.NOUGHT) {
                        g.setColor(new Color(255, 105, 180, 50)); // Brighter pink for player O
                    }
                    g.fillRect(previewCol * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                    break;
                }
            }
        }
    }
}