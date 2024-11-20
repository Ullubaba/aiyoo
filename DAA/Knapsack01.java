import java.util.Scanner;

public class Knapsack01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of products (n): ");
        int n = scanner.nextInt();

        int[] profits = new int[n];
        int[] weights = new int[n];

        System.out.println("Enter the profits of the products:");
        for (int i = 0; i < n; i++) {
            profits[i] = scanner.nextInt();
        }

        System.out.println("Enter the weights of the products:");
        for (int i = 0; i < n; i++) {
            weights[i] = scanner.nextInt();
        }

        System.out.print("Enter the maximum weight allowed: ");
        int maxWeight = scanner.nextInt();

        System.out.println("Weights: ");
        for (int w : weights) {
            System.out.print(w + " ");
        }
        System.out.println("\nProfits: ");
        for (int p : profits) {
            System.out.print(p + " ");
        }

        int[] solution = knapsack(profits, weights, maxWeight);

        System.out.print("\nSelected items: ");
        for (int x : solution) {
            System.out.print(x + " ");
        }
    }

    public static int[] knapsack(int[] profits, int[] weights, int maxWeight) {
        int n = profits.length;
        int[][] dp = new int[n + 1][maxWeight + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= maxWeight; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], profits[i - 1] + dp[i - 1][w - weights[i - 1]]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        int[] result = new int[n];
        int w = maxWeight;
        for (int i = n; i > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                result[i - 1] = 1;
                w -= weights[i - 1];
            } else {
                result[i - 1] = 0;
            }
        }

        return result;
    }
}
