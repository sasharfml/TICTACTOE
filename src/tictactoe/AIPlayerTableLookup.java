//ES234317-Algorithm and Data Structures
//* Semester Ganjil, 2024/2025
//        * Group Capstone Project 2
//        * Group #14
//        * 1 - 5026231151 - Kayla Nathania Azzahra
//* 2 - 5026231202 - Alisha Rafimalia

package tictactoe;

public class AIPlayerTableLookup extends AIPlayer {
    private int[][] preferredMoves = {
            {1, 1}, {0, 0}, {0, 2}, {2, 0}, {2, 2},
            {0, 1}, {1, 0}, {1, 2}, {2, 1}};

    public AIPlayerTableLookup(Seed aiSeed) {
        super(aiSeed);
    }

    @Override
    public int[] makeMove(Cell[][] cells) {
        for (int[] move : preferredMoves) {
            if (cells[move[0]][move[1]].content == Seed.NO_SEED) {
                return move;
            }
        }
        throw new IllegalStateException("No empty cell found!");
    }
}