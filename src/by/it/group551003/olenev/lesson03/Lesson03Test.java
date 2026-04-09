package by.it.group551003.olenev.lesson03;

import org.junit.Test;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertTrue;

public class Lesson03Test {

    private static final String BASE_PATH = "src/by/it/group551003/olenev/lesson03/";

    @Test
    public void checkA() throws Exception {
        try (InputStream inputStream = new FileInputStream(BASE_PATH + "dataA.txt")) {
            A_Huffman instance = new A_Huffman();
            String result = instance.encode(inputStream);
            assertTrue("A failed", result.equals("01001100100111"));
        }
    }

    @Test
    public void checkB() throws Exception {
        try (InputStream inputStream = new FileInputStream(BASE_PATH + "dataB.txt")) {
            B_Huffman instance = new B_Huffman();
            String result = instance.decode(inputStream);
            assertTrue("B failed", result.equals("abacabad"));
        }
    }

    @Test
    public void checkC() throws Exception {
        try (InputStream inputStream = new FileInputStream(BASE_PATH + "dataC.txt")) {
            C_HeapMax instance = new C_HeapMax();
            Long res = instance.findMaxValue(inputStream);
            assertTrue("C failed", res == 500L);
        }
    }
}