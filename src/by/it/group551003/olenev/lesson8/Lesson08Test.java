package by.it.group551003.olenev.lesson8;

import org.junit.Test;
import java.io.InputStream;
import static org.junit.Assert.assertEquals;

public class Lesson08Test {

    @Test
    public void A() throws Exception {
        InputStream stream = A_Knapsack.class.getResourceAsStream("dataA.txt");
        A_Knapsack instance = new A_Knapsack();
        int res = instance.getMaxWeight(stream);
        // В dataA.txt данные: 15 3 / 2 8 16 -> максимальный вес = 14
        assertEquals("A failed", 14, res);
    }

    @Test
    public void B() throws Exception {
        InputStream stream = B_Knapsack.class.getResourceAsStream("dataB.txt");
        B_Knapsack instance = new B_Knapsack();
        int res = instance.getMaxWeight(stream);
        // Для файла с данными 10 3 / 1 4 8 -> должно быть 9 (рюкзак без повторов)
        assertEquals("B failed", 9, res);
    }

    @Test
    public void C() throws Exception {
        InputStream stream = C_Stairs.class.getResourceAsStream("dataC.txt");
        C_Stairs instance = new C_Stairs();
        int res = instance.getMaxSum(stream);
        // Для файла с данными 3 / -1 2 1 -> максимальная сумма = 3
        assertEquals("C failed", 3, res);
    }
}