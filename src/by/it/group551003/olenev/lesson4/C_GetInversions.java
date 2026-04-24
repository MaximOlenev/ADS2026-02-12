package by.it.group551003.olenev.lesson4;

import java.io.InputStream;
import java.util.Scanner;

public class C_GetInversions {

    public static void main(String[] args) throws java.io.FileNotFoundException {
        InputStream stream = C_GetInversions.class.getResourceAsStream("dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        int result = instance.calc(stream);
        System.out.print(result);
    }

    public int calc(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        return mergeSortAndCount(a, 0, n - 1);
    }

    private int mergeSortAndCount(int[] a, int left, int right) {
        if (left >= right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        int leftInv = mergeSortAndCount(a, left, mid);
        int rightInv = mergeSortAndCount(a, mid + 1, right);
        int mergeInv = mergeAndCount(a, left, mid, right);
        return leftInv + rightInv + mergeInv;
    }

    private int mergeAndCount(int[] a, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        int invCount = 0;

        while (i <= mid && j <= right) {
            if (a[i] <= a[j]) {
                temp[k++] = a[i++];
            } else {
                // a[i] > a[j] → все оставшиеся в левой части образуют инверсии с a[j]
                invCount += (mid - i + 1);
                temp[k++] = a[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        while (j <= right) {
            temp[k++] = a[j++];
        }
        for (i = left, k = 0; i <= right; i++, k++) {
            a[i] = temp[k];
        }
        return invCount;
    }
}