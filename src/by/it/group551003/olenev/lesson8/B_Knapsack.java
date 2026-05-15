package by.it.group551003.olenev.lesson8;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class B_Knapsack {

    int getMaxWeight(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        int W = scanner.nextInt();
        int n = scanner.nextInt();
        int[] gold = new int[n];
        for (int i = 0; i < n; i++) {
            gold[i] = scanner.nextInt();
        }

        // dp[w] = максимальный вес для вместимости w
        int[] dp = new int[W + 1];

        for (int i = 0; i < n; i++) {
            // идём справа налево, чтобы каждый слиток использовать не более одного раза
            for (int w = W; w >= gold[i]; w--) {
                dp[w] = Math.max(dp[w], dp[w - gold[i]] + gold[i]);
            }
        }
        return dp[W];
    }

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = B_Knapsack.class.getResourceAsStream("dataB.txt");
        B_Knapsack instance = new B_Knapsack();
        int res = instance.getMaxWeight(stream);
        System.out.println(res);
    }
}