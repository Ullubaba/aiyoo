import java.util.Scanner;

public class NQueens {

    static int count = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of N: ");
        int N = scanner.nextInt();
        
        int[][] board = new int[N][N];
        
        solveNQueens(board, 0, N);
        
        System.out.println("Number of solutions: " + count);
        
        scanner.close();
    }

    public static void solveNQueens(int[][] board, int row, int N) {
        if (row == N) {
            printSolution(board, N);
            count++;
            return;
        }

        for (int col = 0; col < N; col++) {
            if (isSafe(board, row, col, N)) {
                board[row][col] = 1;
                solveNQueens(board, row + 1, N);
                board[row][col] = 0;
            }
        }
    }

    public static boolean isSafe(int[][] board, int row, int col, int N) {
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) {
                return false;
            }
        }

        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        for (int i = row, j = col; i >= 0 && j < N; i--, j++) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    public static void printSolution(int[][] board, int N) {
        System.out.println("Solution:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}
