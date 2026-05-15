package by.it.group551003.olenev.lesson8;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class A_Knapsack {

    int getMaxWeight(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        int W = scanner.nextInt();
        int n = scanner.nextInt();
        int[] gold = new int[n];
        for (int i = 0; i < n; i++) {
            gold[i] = scanner.nextInt();
        }

        // dp[w] = максимальный вес, который можно набрать при вместимости w
        int[] dp = new int[W + 1];
        dp[0] = 0;

        for (int w = 1; w <= W; w++) {
            dp[w] = 0;
            for (int i = 0; i < n; i++) {
                if (gold[i] <= w) {
                    dp[w] = Math.max(dp[w], dp[w - gold[i]] + gold[i]);
                }
            }
        }
        return dp[W];
    }

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = A_Knapsack.class.getResourceAsStream("dataA.txt");
        A_Knapsack instance = new A_Knapsack();
        int res = instance.getMaxWeight(stream);
        System.out.println(res);
    }
}