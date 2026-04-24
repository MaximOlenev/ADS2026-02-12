package by.it.group551003.olenev.lesson4;

import java.io.InputStream;
import java.util.Scanner;

public class B_MergeSort {
    public int[] getMergeSort(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        mergeSort(a, 0, n-1);
        return a;
    }
    private void mergeSort(int[] a, int l, int r) {
        if (l < r) {
            int m = l + (r - l)/2;
            mergeSort(a, l, m);
            mergeSort(a, m+1, r);
            merge(a, l, m, r);
        }
    }
    private void merge(int[] a, int l, int m, int r) {
        int[] temp = new int[r-l+1];
        int i = l, j = m+1, k = 0;
        while (i <= m && j <= r) {
            temp[k++] = a[i] <= a[j] ? a[i++] : a[j++];
        }
        while (i <= m) temp[k++] = a[i++];
        while (j <= r) temp[k++] = a[j++];
        for (i = l, k = 0; i <= r; i++, k++) {
            a[i] = temp[k];
        }
    }
}