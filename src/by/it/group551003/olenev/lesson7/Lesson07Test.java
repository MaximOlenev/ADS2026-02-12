package by.it.group551003.olenev.lesson7;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Lesson07Test {

    @Test
    public void A() throws Exception {
        A_EditDist instance = new A_EditDist();
        assertEquals("A1 failed", 0, instance.getDistanceEdinting("ab", "ab"));
        assertEquals("A2 failed", 3, instance.getDistanceEdinting("short", "ports"));
        assertEquals("A3 failed", 5, instance.getDistanceEdinting("distance", "editing"));
    }

    @Test
    public void B() throws Exception {
        B_EditDist instance = new B_EditDist();
        assertEquals("B1 failed", 0, instance.getDistanceEdinting("ab", "ab"));
        assertEquals("B2 failed", 3, instance.getDistanceEdinting("short", "ports"));
        assertEquals("B3 failed", 5, instance.getDistanceEdinting("distance", "editing"));
    }

    @Test
    public void C() throws Exception {
        C_EditDist instance = new C_EditDist();

        // Проверяем количество совпадений (символов '#') через split
        assertEquals("C1 failed", 2, instance.getDistanceEdinting("ab", "ab").split("#").length);
        assertEquals("C2 failed", 4, instance.getDistanceEdinting("short", "ports").split("#").length);
        assertEquals("C3 failed", 5, instance.getDistanceEdinting("distance", "editing").split("#").length);
    }
}