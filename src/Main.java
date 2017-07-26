import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by nishu on 3/4/2017.
 */
public class Main {
    public static void main(String[] args) {
        int[][] board = getBoard();
        boolean[][] hasValues = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                hasValues[i][j] = board[i][j] > 0;
            }
        }
        sudokuSolver(board);
    }

    public static int[][] getBoard() {
        int[][] board = new int[9][9];
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 9; i++) {
            System.out.println("NEXT ROW");
            for (int j = 0; j < 9; j++) {
                System.out.print("Next number: ");
                board[i][j] = scan.nextInt();
            }
        }

        return board;
    }

    public static void sudokuSolver(int[][] board) {
        if (board.length > 9 || board.length == 0 || board[0].length > 9 || board[0].length == 0) {
            System.out.println("Not a valid board! You do know what sudoku is, right?");
        }

        solve(0, 0, board);
        printBoard(board);
    }

    public static void printBoard(int[][]board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean solve(int i, int j, int[][] board) {
        if (j == 9) {
            j = 0;
            if (i+1 == 9) {
                return true;
            }
            i++;
        }
        if (board[i][j] != 0) {
            return solve(i, j+1, board);
        }

        for (int test = 1; test <= 9; test++) {
            if (legal(i , j, test, board)) {
                board[i][j] = test;
                if (solve(i, j+1, board)) {
                    return true;
                }
            }
        }
        board[i][j] = 0;
        return false;
    }

    public static boolean legal(int i, int j, int test, int[][] board) {
        for (int k = 0; k < 9; ++k) {
            if (test == board[k][j]) {
                return false;
            }
        }

        for (int k = 0; k < 9; ++k) {
            if (test == board[i][k]) {
                return false;
            }
        }

        while (i != 0 && i != 3 && i != 6) {
            i--;
        }
        while (j != 0 && j != 3 && j != 6) {
            j--;
        }

        for (int row = i; row < i+3; row++) {
            for (int col = j; col < j+3; col++) {
                if (test == board[row][col]) {
                    return false;
                }
            }
        }

        return true;
    }
}
