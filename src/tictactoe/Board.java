package tictactoe;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Board extends JPanel {
    public static final int ROWS = 3;
    public static final int COLS = 3;
    public static final int CELL_SIZE = 120; // Size of each cell
    public static final int CANVAS_WIDTH = CELL_SIZE * COLS;
    public static final int CANVAS_HEIGHT = CELL_SIZE * ROWS;
    public static final int GRID_WIDTH = 8;
    public static final int GRID_WIDTH_HALF = GRID_WIDTH / 2;
    public static final Color COLOR_GRID = Color.LIGHT_GRAY; // Color for grid and border

    private Cell[][] cells;
    private Seed currentPlayer;
    private State currentState;

    public Board() {
        initGame();
        setPreferredSize(new java.awt.Dimension(CANVAS_WIDTH, CANVAS_HEIGHT + 30)); // Extra space for text

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();
                int rowSelected = mouseY / CELL_SIZE;
                int colSelected = mouseX / CELL_SIZE;

                if (currentState == State.PLAYING) {
                    if (cells[rowSelected][colSelected].content == Seed.NO_SEED) {
                        cells[rowSelected][colSelected].content = currentPlayer;
                        updateGame(currentPlayer, rowSelected, colSelected);
                        if (currentPlayer == Seed.CROSS) {
                            SoundEffect.ALPHABA.play(); // Play sound when player X makes a move
                        } else if (currentPlayer == Seed.NOUGHT) {
                            SoundEffect.GLINDA.play(); // Play sound when player O makes a move
                        }
                        currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
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
            String winner = (player == Seed.CROSS) ? "X" : "O";
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(this, "Congratulations! Player " + winner + " is the Winner!!");
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
        g.setColor(COLOR_GRID);
        for (int row = 1; row < ROWS; ++row) {
            g.fillRoundRect(0, CELL_SIZE * row - GRID_WIDTH_HALF,
                    CANVAS_WIDTH - 1, GRID_WIDTH,
                    GRID_WIDTH, GRID_WIDTH);
        }
        for (int col = 1; col < COLS; ++col) {
            g.fillRoundRect(CELL_SIZE * col - GRID_WIDTH_HALF, 0,
                    GRID_WIDTH, CANVAS_HEIGHT - 1,
                    GRID_WIDTH, GRID_WIDTH);
        }

        // Draw the border around the grid with the same thickness and color as the grid lines
        g.fillRect(0, 0, CANVAS_WIDTH, GRID_WIDTH); // Top border
        g.fillRect(0, 0, GRID_WIDTH, CANVAS_HEIGHT); // Left border
        g.fillRect(CANVAS_WIDTH - GRID_WIDTH, 0, GRID_WIDTH, CANVAS_HEIGHT); // Right border
        g.fillRect(0, CANVAS_HEIGHT - GRID_WIDTH, CANVAS_WIDTH, GRID_WIDTH); // Bottom border

        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col].paint(g);
            }
        }

        // Draw the current player's turn below the board
        g.setColor(Color.BLACK);
        String turnText = (currentPlayer == Seed.CROSS) ? "X's Turn" : "O's Turn";
        g.drawString(turnText, 10, CANVAS_HEIGHT + 20);
    }
}