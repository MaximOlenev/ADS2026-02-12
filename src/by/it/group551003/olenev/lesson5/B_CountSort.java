// ==================== Файл B_CountSort.java ====================
package by.it.group551003.olenev.lesson5;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class B_CountSort {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = B_CountSort.class.getResourceAsStream("dataB.txt");
        B_CountSort instance = new B_CountSort();
        int[] result = instance.countSort(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

    int[] countSort(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] points = new int[n];
        for (int i = 0; i < n; i++) {
            points[i] = scanner.nextInt();
        }

        // Числа не превышают 10, создаём счётчик на 11 элементов (0..10)
        int[] count = new int[11];
        for (int val : points) {
            count[val]++;
        }

        int index = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i]-- > 0) {
                points[index++] = i;
            }
        }
        return points;
    }
}