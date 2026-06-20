import java.util.*;

public class ZerodhaOrderBookEngine {
    static class Order {
        String id;
        double price;
        long timestamp;

        Order(String id, double price, long timestamp) {
            this.id = id;
            this.price = price;
            this.timestamp = timestamp;
        }
    }

    public static void mergeSort(Order[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private static void merge(Order[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        Order[] L = new Order[n1];
        Order[] R = new Order[n2];
        System.arraycopy(arr, l, L, 0, n1);
        System.arraycopy(arr, m + 1, R, 0, n2);

        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (L[i].price <= R[j].price) { 
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    public static void quickSort(Order[] arr, int low, int high) {
        if (low < high) {
            int pi = randomizedPartition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int randomizedPartition(Order[] arr, int low, int high) {
        int randomIdx = low + new Random().nextInt(high - low + 1);
        swap(arr, randomIdx, high);
        return partition(arr, low, high);
    }

    private static int partition(Order[] arr, int low, int high) {
        double pivot = arr[high].price;
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j].price < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(Order[] arr, int i, int j) {
        Order temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println("⚡ Benchmarking Zerodha Kite Order Book Matching Systems...");
        int size = 5000;
        Order[] masterList = new Order[size];
        Random rand = new Random();

        for (int i = 0; i < size; i++) {
            masterList[i] = new Order("ORD_" + i, 100.0 + rand.nextDouble() * 500, System.nanoTime());
        }

        Order[] arrMerge = Arrays.copyOf(masterList, masterList.length);
        long start = System.nanoTime();
        mergeSort(arrMerge, 0, arrMerge.length - 1);
        long durationMerge = System.nanoTime() - start;

        Order[] arrQuick = Arrays.copyOf(masterList, masterList.length);
        start = System.nanoTime();
        quickSort(arrQuick, 0, arrQuick.length - 1);
        long durationQuick = System.nanoTime() - start;

        System.out.println("\n--- Performance Benchmarking Metrics [" + size + " Orders] ---");
        System.out.println("📊 Merge Sort Execution Time : " + (durationMerge / 1_000_000.0) + " ms (Stable Sort)");
        System.out.println("📊 Quick Sort Execution Time : " + (durationQuick / 1_000_000.0) + " ms (In-place Cache Efficient)");
    }
}