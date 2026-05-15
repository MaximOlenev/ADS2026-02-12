package by.it.group551003.olenev.lesson7;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class C_EditDist {

    String getDistanceEdinting(String one, String two) {
        int n = one.length();
        int m = two.length();
        int[][] dp = new int[n + 1][m + 1];
        String[][] back = new String[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
            back[i][0] = "D";
        }
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
            back[0][j] = "I";
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (one.charAt(i - 1) == two.charAt(j - 1)) {
                    // Если символы совпадают, всегда выбираем совпадение (копирование)
                    dp[i][j] = dp[i - 1][j - 1];
                    back[i][j] = "M";
                } else {
                    int del = dp[i - 1][j] + 1;
                    int ins = dp[i][j - 1] + 1;
                    int sub = dp[i - 1][j - 1] + 1;

                    if (del <= ins && del <= sub) {
                        dp[i][j] = del;
                        back[i][j] = "D";
                    } else if (ins <= del && ins <= sub) {
                        dp[i][j] = ins;
                        back[i][j] = "I";
                    } else {
                        dp[i][j] = sub;
                        back[i][j] = "R";
                    }
                }
            }
        }

        // Восстановление строки предписания
        StringBuilder sb = new StringBuilder();
        int i = n, j = m;
        while (i > 0 || j > 0) {
            String op = back[i][j];
            switch (op) {
                case "D":
                    sb.append("-").append(one.charAt(i - 1)).append(",");
                    i--;
                    break;
                case "I":
                    sb.append("+").append(two.charAt(j - 1)).append(",");
                    j--;
                    break;
                case "R":
                    sb.append("~").append(two.charAt(j - 1)).append(",");
                    i--;
                    j--;
                    break;
                case "M":
                    sb.append("#,");
                    i--;
                    j--;
                    break;
            }
        }

        // Разворачиваем
        String result = sb.reverse().toString();
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = C_EditDist.class.getResourceAsStream("dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }
}