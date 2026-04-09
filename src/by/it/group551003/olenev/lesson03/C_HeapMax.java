package by.it.group551003.olenev.lesson03;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class C_HeapMax {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = C_HeapMax.class.getResourceAsStream("dataC.txt");
        C_HeapMax instance = new C_HeapMax();
        System.out.println("MAX=" + instance.findMaxValue(stream));
    }

    Long findMaxValue(InputStream stream) {
        if (stream == null) return 0L;
        Long maxValue = 0L;
        MaxHeap heap = new MaxHeap();
        Scanner scanner = new Scanner(stream);
        int count = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < count; i++) {
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("extractMax")) {
                Long res = heap.extractMax();
                if (res != null && res > maxValue) maxValue = res;
            } else if (line.startsWith("Insert")) {
                String[] parts = line.split(" ");
                heap.insert(Long.parseLong(parts[1]));
            }
        }
        return maxValue;
    }

    private class MaxHeap {
        private List<Long> heap = new ArrayList<>();

        private int siftUp(int i) {
            while (i > 0) {
                int parent = (i - 1) / 2;
                if (heap.get(parent) >= heap.get(i)) break;
                swap(i, parent);
                i = parent;
            }
            return i;
        }

        private int siftDown(int i) {
            int size = heap.size();
            while (true) {
                int left = 2 * i + 1;
                int right = 2 * i + 2;
                int largest = i;
                if (left < size && heap.get(left) > heap.get(largest)) largest = left;
                if (right < size && heap.get(right) > heap.get(largest)) largest = right;
                if (largest == i) break;
                swap(i, largest);
                i = largest;
            }
            return i;
        }

        private void swap(int i, int j) {
            Long temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }

        void insert(Long value) {
            heap.add(value);
            siftUp(heap.size() - 1);
        }

        Long extractMax() {
            if (heap.isEmpty()) return null;
            Long max = heap.get(0);
            int last = heap.size() - 1;
            heap.set(0, heap.get(last));
            heap.remove(last);
            if (!heap.isEmpty()) siftDown(0);
            return max;
        }
    }
}