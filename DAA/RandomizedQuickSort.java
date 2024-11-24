import java.util.Random;

public class RandomizedQuickSort {
    public static void main(String[] args) {
        int n = 500;
        int[] arr = new int[n];
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(500);
        }

        System.out.println("Unsorted Array:");
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();

        long startTime = System.nanoTime();

        randomizedQuickSort(arr, 0, n - 1);

        long endTime = System.nanoTime();

        System.out.println("Sorted Array:");
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();

        long durationNano = endTime - startTime;
        double durationMilli = durationNano / 1_000_000.0;
        double durationSeconds = durationNano / 1_000_000_000.0;

        System.out.println("\nExecution Time:");
        System.out.printf("In nanoseconds: %d ns%n", durationNano);
        System.out.printf("In milliseconds: %.3f ms%n", durationMilli);
        System.out.printf("In seconds: %.6f s%n", durationSeconds);
    }

    public static void randomizedQuickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = randomizedPartition(arr, low, high);
            randomizedQuickSort(arr, low, pi - 1);
            randomizedQuickSort(arr, pi + 1, high);
        }
    }

    public static int randomizedPartition(int[] arr, int low, int high) {
        Random rand = new Random();
        int randomPivotIndex = low + rand.nextInt(high - low + 1);

        // Swap the randomly chosen pivot with the element at the high index
        int temp = arr[randomPivotIndex];
        arr[randomPivotIndex] = arr[high];
        arr[high] = temp;

        return partition(arr, low, high);
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
}
