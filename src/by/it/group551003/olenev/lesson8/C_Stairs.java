package by.it.group551003.olenev.lesson8;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class C_Stairs {

    int getMaxSum(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] stairs = new int[n];
        for (int i = 0; i < n; i++) {
            stairs[i] = scanner.nextInt();
        }

        if (n == 0) return 0;
        // dp[i] – максимальная сумма при достижении i-й ступеньки (ступеньки нумеруются с 1)
        // dp[0] = 0 – начальная позиция (нулевая ступенька)
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = stairs[0];                 // с первой ступеньки
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2]) + stairs[i - 1];
        }
        return dp[n];
    }

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = C_Stairs.class.getResourceAsStream("dataC.txt");
        C_Stairs instance = new C_Stairs();
        int res = instance.getMaxSum(stream);
        System.out.println(res);
    }
}