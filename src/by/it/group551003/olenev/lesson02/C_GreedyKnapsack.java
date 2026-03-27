package by.it.group551003.olenev.lesson02;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class C_GreedyKnapsack {
    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        InputStream inputStream = C_GreedyKnapsack.class.getResourceAsStream("greedyKnapsack.txt");
        double costFinal = new C_GreedyKnapsack().calc(inputStream);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)", costFinal, finishTime - startTime);
    }

    double calc(InputStream inputStream) throws FileNotFoundException {
        Scanner input = new Scanner(inputStream);
        int n = input.nextInt();
        int W = input.nextInt();
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(input.nextInt(), input.nextInt());
        }

        for (Item item : items) {
            System.out.println(item);
        }
        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n", n, W);


        double result = 0;


        for (int i = 0; i < items.length - 1; i++) {
            for (int j = i + 1; j < items.length; j++) {

                double valuePerWeight1 = (double) items[i].cost / items[i].weight;
                double valuePerWeight2 = (double) items[j].cost / items[j].weight;
                if (valuePerWeight1 < valuePerWeight2) {
                    Item temp = items[i];
                    items[i] = items[j];
                    items[j] = temp;
                }
            }
        }

        int remainingCapacity = W;
        for (Item item : items) {
            if (remainingCapacity == 0) {
                break;
            }

            if (item.weight <= remainingCapacity) {

                result += item.cost;
                remainingCapacity -= item.weight;
                System.out.printf("Берем целиком: %s (стоимость за кг: %.2f)\n",
                        item, (double) item.cost / item.weight);
            } else {

                double fraction = (double) remainingCapacity / item.weight;
                result += item.cost * fraction;
                System.out.printf("Берем часть (%.2f%%): %s (стоимость за кг: %.2f)\n",
                        fraction * 100, item, (double) item.cost / item.weight);
                remainingCapacity = 0;
            }
        }

        System.out.printf("Удалось собрать рюкзак на сумму %f\n", result);
        return result;
    }

    private static class Item implements Comparable<Item> {
        int cost;
        int weight;

        Item(int cost, int weight) {
            this.cost = cost;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "cost=" + cost +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public int compareTo(Item o) {

            double valuePerWeight1 = (double) this.cost / this.weight;
            double valuePerWeight2 = (double) o.cost / o.weight;

            if (valuePerWeight1 > valuePerWeight2) {
                return -1;
            } else if (valuePerWeight1 < valuePerWeight2) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}