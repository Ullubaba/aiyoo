import java.util.Random;

public class quicksort500val {
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
        
        quickSort(arr, 0, n - 1);

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

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
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
