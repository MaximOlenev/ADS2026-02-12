// ==================== Файл C_QSortOptimized.java ====================
package by.it.group551003.olenev.lesson5;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class C_QSortOptimized {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = C_QSortOptimized.class.getResourceAsStream("dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result = instance.getAccessory2(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            if (a > b) {
                int tmp = a;
                a = b;
                b = tmp;
            }
            segments[i] = new Segment(a, b);
        }

        int[] points = new int[m];
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }

        quickSort3Way(segments, 0, n - 1);

        int[] stops = new int[n];
        for (int i = 0; i < n; i++) {
            stops[i] = segments[i].stop;
        }
        quickSortInt(stops, 0, n - 1);

        int[] result = new int[m];
        for (int i = 0; i < m; i++) {
            int point = points[i];
            int cntStart = upperBoundSegments(segments, point);
            int cntStop  = lowerBoundInt(stops, point);
            result[i] = cntStart - cntStop;
        }
        return result;
    }

    private void quickSort3Way(Segment[] a, int left, int right) {
        while (left < right) {
            int lt = left;
            int gt = right;
            Segment pivot = a[left];
            int i = left;
            while (i <= gt) {
                int cmp = a[i].compareTo(pivot);
                if (cmp < 0) {
                    swapSegments(a, lt++, i++);
                } else if (cmp > 0) {
                    swapSegments(a, i, gt--);
                } else {
                    i++;
                }
            }
            if (lt - left < right - gt) {
                quickSort3Way(a, left, lt - 1);
                left = gt + 1;
            } else {
                quickSort3Way(a, gt + 1, right);
                right = lt - 1;
            }
        }
    }

    private void swapSegments(Segment[] a, int i, int j) {
        Segment tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    // ---------------------------------------------------------------
    // Быстрая сортировка для int[] (с устранением хвостовой рекурсии)
    // ---------------------------------------------------------------
    private void quickSortInt(int[] a, int left, int right) {
        while (left < right) {
            int pivotIndex = partitionInt(a, left, right);
            if (pivotIndex - left < right - pivotIndex) {
                quickSortInt(a, left, pivotIndex - 1);
                left = pivotIndex + 1;
            } else {
                quickSortInt(a, pivotIndex + 1, right);
                right = pivotIndex - 1;
            }
        }
    }

    private int partitionInt(int[] a, int left, int right) {
        int pivot = a[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (a[j] <= pivot) {
                i++;
                swapInt(a, i, j);
            }
        }
        swapInt(a, i + 1, right);
        return i + 1;
    }

    private void swapInt(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    // ---------------------------------------------------------------
    // Бинарные поиски
    // ---------------------------------------------------------------
    // Количество отрезков с start <= point (по массиву segments, отсортированному по start)
    private int upperBoundSegments(Segment[] seg, int point) {
        int lo = 0, hi = seg.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (seg[mid].start <= point) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    // Количество элементов < x в отсортированном массиве int[]
    private int lowerBoundInt(int[] arr, int x) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] < x) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    // ---------------------------------------------------------------
    // Вспомогательный класс Segment
    // ---------------------------------------------------------------
    private static class Segment implements Comparable<Segment> {
        int start;
        int stop;

        Segment(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment o) {
            // Сравниваем по start, при равенстве – по stop
            if (this.start != o.start) return Integer.compare(this.start, o.start);
            return Integer.compare(this.stop, o.stop);
        }
    }
}