package by.it.group551003.olenev.lesson4;

import java.io.InputStream;
import java.util.Scanner;

public class A_BinaryFind {
    public int[] findIndex(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 1; i <= n; i++) {
            a[i-1] = scanner.nextInt();
        }
        int k = scanner.nextInt();
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            int value = scanner.nextInt();
            int left = 0, right = n-1, found = -1;
            while (left <= right) {
                int mid = left + (right - left)/2;
                if (a[mid] == value) {
                    found = mid + 1;
                    break;
                } else if (a[mid] < value) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            result[i] = found;
        }
        return result;
    }
}