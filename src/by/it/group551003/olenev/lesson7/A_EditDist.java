package by.it.group551003.olenev.lesson7;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class A_EditDist {

    private String s1, s2;
    private int[][] memo;

    int getDistanceEdinting(String one, String two) {
        s1 = one;
        s2 = two;
        int n = one.length();
        int m = two.length();
        memo = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                memo[i][j] = -1;
            }
        }
        return lev(n, m);
    }

    private int lev(int i, int j) {
        if (memo[i][j] != -1) return memo[i][j];
        if (i == 0) {
            memo[i][j] = j;
        } else if (j == 0) {
            memo[i][j] = i;
        } else {
            int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
            int del = lev(i - 1, j) + 1;
            int ins = lev(i, j - 1) + 1;
            int sub = lev(i - 1, j - 1) + cost;
            memo[i][j] = Math.min(del, Math.min(ins, sub));
        }
        return memo[i][j];
    }

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = A_EditDist.class.getResourceAsStream("dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }
}