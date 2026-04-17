package by.it.group551003.olenev.lesson03;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class B_Huffman {

    public static void main(String[] args) {
        InputStream inputStream = B_Huffman.class.getResourceAsStream("dataB.txt");
        B_Huffman instance = new B_Huffman();
        String result = instance.decode(inputStream);
        System.out.println(result);
    }

    String decode(InputStream inputStream) {
        StringBuilder result = new StringBuilder();
        Scanner scanner = new Scanner(inputStream);

        int count = scanner.nextInt();
        int length = scanner.nextInt();
        scanner.nextLine();

        Map<String, Character> codeToChar = new HashMap<>();
        for (int i = 0; i < count; i++) {
            String line;

            do {
                line = scanner.nextLine().trim();
            } while (line.isEmpty());

            String[] parts = line.split(":\\s*");
            char letter = parts[0].charAt(0);
            String code = parts[1];
            codeToChar.put(code, letter);
        }

        String encoded = scanner.next();

        StringBuilder currentCode = new StringBuilder();
        for (char bit : encoded.toCharArray()) {
            currentCode.append(bit);
            String code = currentCode.toString();
            if (codeToChar.containsKey(code)) {
                result.append(codeToChar.get(code));
                currentCode.setLength(0);
            }
        }
        scanner.close();
        return result.toString();
    }
}