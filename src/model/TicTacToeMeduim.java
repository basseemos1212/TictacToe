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
import java.util.ArrayList;
import java.util.Random;

public class TicTacToeMeduim {
    private char[] board;
    private char player;
    private char computer;
    private Random random;

    public TicTacToeMeduim() {
        board = new char[9];
        for (int i = 0; i < board.length; i++) {
            board[i] = ' ';
        }
        player = 'X';
        computer = 'O';
        random = new Random();
    }

    public void printBoard() {
        System.out.println(" " + board[0] + " | " + board[1] + " | " + board[2] + " ");
        System.out.println("---+---+---");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5] + " ");
        System.out.println("---+---+---");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + " ");
    }

    public ArrayList<Integer> getFreeCells() {
        ArrayList<Integer> freeCells = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            if (board[i] == ' ') {
                freeCells.add(i);
            }
        }
        return freeCells;
    }

    public int getRandomMove() {
        ArrayList<Integer> freeCells = getFreeCells();
        return freeCells.get(random.nextInt(freeCells.size())) + 1;
    }

    public Integer getWinningMove(char mark) {
        ArrayList<Integer> freeCells = getFreeCells();
        for (int i = 0; i < freeCells.size(); i++) {
            int cell = freeCells.get(i);
            char[] boardCopy = board.clone();
            boardCopy[cell] = mark;
            if (checkWin(boardCopy, mark)) {
                return cell + 1;
            }
        }
        return null;
    }

    public boolean checkWin(char[] board, char mark) {
        return ((board[0] == mark && board[1] == mark && board[2] == mark) ||
                (board[3] == mark && board[4] == mark && board[5] == mark) ||
                (board[6] == mark && board[7] == mark && board[8] == mark) ||
                (board[0] == mark && board[3] == mark && board[6] == mark) ||
                (board[1] == mark && board[4] == mark && board[7] == mark) ||
                (board[2] == mark && board[5] == mark && board[8] == mark) ||
                (board[0] == mark && board[4] == mark && board[8] == mark) ||
                (board[2] == mark && board[4] == mark && board[6] == mark));
    }

    public int getComputerMove() {
        // Check if computer can win in next move
        Integer winningMove = getWinningMove(computer);
        if (winningMove != null) {
            return winningMove;
        }

        // Check if player can win in next move and block it
        Integer blockingMove = getWinningMove(player);
        if (blockingMove != null) {
            return blockingMove;
        }

        // If center cell is free, choose it
        if (board[4] == ' ') {
            return 5;
        }

        // If player has a mark in a corner, choose the opposite corner
        int[] corners = {1, 3, 7, 9};
        ArrayList<Integer> playerCorners = new ArrayList<>();
        for (int i = 0; i < corners.length; i++) {
            if (board[corners[i] - 1] == player) {
                playerCorners.add(corners[i]);
            }
        }
        if (!playerCorners.isEmpty()) {
            ArrayList<Integer> oppositeCorners = new ArrayList<>();
            for (int i = 0; i < corners.length; i++) {
                if (!playerCorners.contains(corners[i]) && board[corners[i] - 1] == ' ') {
                    oppositeCorners.add(corners[i]);
                }
            }
            if (!oppositeCorners.isEmpty()) {
                return oppositeCorners.get(random.nextInt(oppositeCorners.size()));
            }
        }

        // Choose a random move
        ArrayList<Integer> freeCells = getFreeCells();
        return freeCells.get(random.nextInt(freeCells.size())) + 1;
    }

    public boolean makeMove(int cell) {
        if (cell < 1 || cell > 9) {
            return false;
        }
        int index = cell - 1;
        if (board[index] != ' ') {
            return false;
        }
        board[index] = player;
        if (checkWin(board, player)) {
            printBoard();
            System.out.println("You win!");
            return true;
        } else if (getFreeCells().isEmpty()) {
            printBoard();
            System.out.println("Tie game.");
            return true;
        } else {
            board[getComputerMove() - 1] = computer;
            if (checkWin(board, computer)) {
                printBoard();
                System.out.println("Computer wins.");
                return true;
            } else if (getFreeCells().isEmpty()) {
                printBoard();
                System.out.println("Tie game.");
                return true;
            } else {
                printBoard();
                return false;
            }
        }
    }
}