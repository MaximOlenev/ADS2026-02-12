package by.it.group551003.olenev.lesson6;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class C_LongNotUpSubSeq {
    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = C_LongNotUpSubSeq.class.getResourceAsStream("dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(stream);
        System.out.print(result);
    }

    int getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = -a[i];
        }

        int[] tails = new int[n];
        int[] bestIndex = new int[n];
        int[] prev = new int[n];
        int length = 0;

        for (int i = 0; i < n; i++) {
            int x = b[i];

            int left = 0, right = length;
            while (left < right) {
                int mid = (left + right) / 2;
                if (tails[mid] <= x) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            int pos = left;
            if (pos == length) {
                length++;
            }
            tails[pos] = x;
            bestIndex[pos] = i;

            if (pos == 0) {
                prev[i] = -1;
            } else {
                prev[i] = bestIndex[pos - 1];
            }
        }
        List<Integer> indices = new ArrayList<>();
        int cur = bestIndex[length - 1];
        while (cur != -1) {
            indices.add(cur + 1);
            cur = prev[cur];
        }

        return length;
    }
}