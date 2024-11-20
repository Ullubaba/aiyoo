import java.util.Scanner;

public class threadedmul {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the matrix (n*n): ");
        int n = scanner.nextInt();

        int[][] A = new int[n][n];
        int[][] B = new int[n][n];

        System.out.println("Enter elements of matrix A:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Enter elements of matrix B:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                B[i][j] = scanner.nextInt();
            }
        }

        int[][] result = matrixMultiplication(A, B);

        System.out.println("Matrix A:");
        printMat(A);
        System.out.println("Matrix B:");
        printMat(B);
        System.out.println("Result of Matrix Multiplication:");
        printMat(result);
    }

    static int[][] matrixMultiplication(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];

        Thread[] threads = new Thread[n];
        for (int i = 0; i < n; i++) {
            int row = i;
            threads[i] = new Thread(() -> computeRow(A, B, result, row));
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    private static void computeRow(int[][] A, int[][] B, int[][] result, int row) {
        int n = A.length;
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                result[row][j] += A[row][k] * B[k][j];
            }
        }
    }

    private static void printMat(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
