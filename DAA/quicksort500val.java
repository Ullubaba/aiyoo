import java.util.Random;

public class quicksort500val {
    public static void main(String[] args) {
    int n = 500;
    int[] arr = new int[n];
    Random rand = new Random();
    
    // Create array with random values
    for (int i = 0; i < n; i++) {
        arr[i] = rand.nextInt(500);
    }
    
    System.out.println("Unsorted Array:");
    for (int value : arr) {
        System.out.print(value + " ");
    }
    System.out.println();

    // Start time measurement
    long startTime = System.nanoTime();
    
    // Perform merge sort
    mergeSort(arr, 0, n - 1);
    
    // End time measurement
    long endTime = System.nanoTime();
    
    System.out.println("Sorted Array:");
    for (int value : arr) {
        System.out.print(value + " ");
    }
    System.out.println();
    
    // Calculate and display execution time in different units
    long timeElapsedNanos = endTime - startTime;
    double timeElapsedMillis = timeElapsedNanos / 1_000_000.0;
    double timeElapsedSeconds = timeElapsedNanos / 1_000_000_000.0;
    
    System.out.println("\nExecution Time:");
    System.out.printf("Nanoseconds: %d ns%n", timeElapsedNanos);
    System.out.printf("Milliseconds: %.3f ms%n", timeElapsedMillis);
    System.out.printf("Seconds: %.6f s%n", timeElapsedSeconds);
}

public static void mergeSort(int[] arr, int left, int right) {
    if (left < right) {
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }
}

public static void merge(int[] arr, int left, int mid, int right) {
    int n1 = mid - left + 1;
    int n2 = right - mid;
    int[] leftArr = new int[n1];
    int[] rightArr = new int[n2];
    
    for (int i = 0; i < n1; i++) {
        leftArr[i] = arr[left + i];
    }
    for (int j = 0; j < n2; j++) {
        rightArr[j] = arr[mid + 1 + j];
    }
    
    int i = 0, j = 0, k = left;
    while (i < n1 && j < n2) {
        if (leftArr[i] <= rightArr[j]) {
            arr[k] = leftArr[i];
            i++;
        } else {
            arr[k] = rightArr[j];
            j++;
        }
        k++;
    }
    
    while (i < n1) {
        arr[k] = leftArr[i];
        i++;
        k++;
    }
    
    while (j < n2) {
        arr[k] = rightArr[j];
        j++;
        k++;
    }
}
}
