/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Bassem
 */
import java.util.Arrays;

public class TicTacToeHard {
  
    
   
    
    private char[] board;
    private static final int BOARD_SIZE = 9;
    private static final char HUMAN_PLAYER = 'X';
    private static final char COMPUTER_PLAYER = 'O';

    public TicTacToeHard() {
        board = new char[BOARD_SIZE];
        Arrays.fill(board, ' ');
    }

    public void displayBoard() {
        System.out.println();
        System.out.println(" " + board[0] + " | " + board[1] + " | " + board[2] + " ");
        System.out.println("---+---+---");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5] + " ");
        System.out.println("---+---+---");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + " ");
        System.out.println();
    }

    public boolean isBoardFull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i] == ' ') {
                return false;
            }
        }
        return true;
    }

    public boolean isGameOver() {
        return hasPlayerWon(HUMAN_PLAYER) || hasPlayerWon(COMPUTER_PLAYER) || isBoardFull();
    }

    public boolean hasPlayerWon(char player) {
        if ((board[0] == player && board[1] == player && board[2] == player) ||
                (board[3] == player && board[4] == player && board[5] == player) ||
                (board[6] == player && board[7] == player && board[8] == player) ||
                (board[0] == player && board[3] == player && board[6] == player) ||
                (board[1] == player && board[4] == player && board[7] == player) ||
                (board[2] == player && board[5] == player && board[8] == player) ||
                (board[0] == player && board[4] == player && board[8] == player) ||
                (board[2] == player && board[4] == player && board[6] == player)) {
            return true;
        }
        return false;
    }

    public boolean playMove(int cell, char player) {
        if (board[cell] != ' ') {
            return false;
        }
        board[cell] = player;
        return true;
    }

    public int getComputerMove() {
        int bestScore = Integer.MIN_VALUE;
        int bestMove = -1;
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i] == ' ') {
                board[i] = COMPUTER_PLAYER;
                int score = minimax(false, 0);
                board[i] = ' ';
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i;
                }
            }
        }
        return bestMove;
    }

    private int minimax(boolean isMaximizingPlayer, int depth) {
        if (hasPlayerWon(HUMAN_PLAYER)) {
            return -10 + depth;
        } else if (hasPlayerWon(COMPUTER_PLAYER)) {
            return 10 - depth;
        } else if (isBoardFull()) {
            return 0;
        }
        if (isMaximizingPlayer) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < BOARD_SIZE; i++) {
                if (board[i] == ' ') {
                    board[i] = COMPUTER_PLAYER;
                    int score = minimax(false, depth + 1);
                    board[i] = ' ';
                    bestScore = Math.max(bestScore, score);
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < BOARD_SIZE; i++) {
                if (board[i] == ' ') {
                    board[i] = HUMAN_PLAYER;
                    int score = minimax(true, depth + 1);
                    board[i] = ' ';
                    bestScore = Math.min(bestScore, score);
                }
            }
            return bestScore;
        }
    }

    
}
