// ==================== Файл A_QSort.java ====================
package by.it.group551003.olenev.lesson5;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class A_QSort {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = A_QSort.class.getResourceAsStream("dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result = instance.getAccessory(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

    int[] getAccessory(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int[] starts = new int[n];
        int[] stops = new int[n];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            stops[i] = scanner.nextInt();
            if (starts[i] > stops[i]) {
                int tmp = starts[i];
                starts[i] = stops[i];
                stops[i] = tmp;
            }
        }

        int[] points = new int[m];
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }

        quickSort(starts, 0, n - 1);
        quickSort(stops, 0, n - 1);

        int[] result = new int[m];
        for (int i = 0; i < m; i++) {
            int point = points[i];
            int cntStart = upperBound(starts, point); // кол-во start <= point
            int cntStop = lowerBound(stops, point);   // кол-во stop < point
            result[i] = cntStart - cntStop;
        }
        return result;
    }

    // Быстрая сортировка для int[]
    private void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pivot = partition(arr, left, right);
            quickSort(arr, left, pivot - 1);
            quickSort(arr, pivot + 1, right);
        }
    }

    private int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, right);
        return i + 1;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // Возвращает количество элементов <= x
    private int upperBound(int[] arr, int x) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] <= x) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    // Возвращает количество элементов < x
    private int lowerBound(int[] arr, int x) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] < x) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}