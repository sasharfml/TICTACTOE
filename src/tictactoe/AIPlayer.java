//ES234317-Algorithm and Data Structures
//* Semester Ganjil, 2024/2025
//        * Group Capstone Project 2
//        * Group #14
//        * 1 - 5026231151 - Kayla Nathania Azzahra
//* 2 - 5026231202 - Alisha Rafimalia

package tictactoe;

public class AIPlayer {
    private Seed aiSeed;
    private Seed opponentSeed;

    public AIPlayer(Seed aiSeed) {
        this.aiSeed = aiSeed;
        this.opponentSeed = (aiSeed == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
    }

    public int[] makeMove(Cell[][] cells) {
        // Implement AI logic here (e.g., Minimax algorithm)
        // For simplicity, let's assume it returns the first available cell
        for (int row = 0; row < Board.ROWS; ++row) {
            for (int col = 0; col < Board.COLS; ++col) {
                if (cells[row][col].content == Seed.NO_SEED) {
                    return new int[]{row, col};
                }
            }
        }
        return null; // No move possible
    }

    public Seed getAiSeed() {
        return this.aiSeed;
    }
}