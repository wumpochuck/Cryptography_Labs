/*
 * Задание 3: Придумайте свой однозвучный подстановочный шифр,
 * реализуйте его на любом языке программирования, зашифруйте с помощью
 * вашего шифра произвольное сообщение, после чего дешифруйте его.
 * Опишите достоинства и недостатки шифра.
 */

package Simple_symmetric_ciphers;

import java.util.HashMap;
import java.util.Map;

public class SimpleSubstitutionCipher {
    // Таблицы для шифрования и дешифрования
    private static final Map<Character, Character> encryptMap = new HashMap<>();
    private static final Map<Character, Character> decryptMap = new HashMap<>();

    // Метод для создания подстановочных таблиц
    private static void createSubstitutionMap(String originalAlphabet, String substitutionAlphabet) {
        for (int i = 0; i < originalAlphabet.length(); i++) {
            char originalChar = originalAlphabet.charAt(i);
            char substitutionChar = substitutionAlphabet.charAt(i);
            encryptMap.put(originalChar, substitutionChar);
            decryptMap.put(substitutionChar, originalChar);
        }
    }

    // Метод для шифрования сообщения
    private static String encrypt(String message) {
        StringBuilder encryptedMessage = new StringBuilder();
        for (char c : message.toCharArray()) {
            encryptedMessage.append(encryptMap.getOrDefault(c, c));
        }
        return encryptedMessage.toString();
    }

    // Метод для дешифрования сообщения
    private static String decrypt(String encryptedMessage) {
        StringBuilder decryptedMessage = new StringBuilder();
        for (char c : encryptedMessage.toCharArray()) {
            decryptedMessage.append(decryptMap.getOrDefault(c, c));
        }
        return decryptedMessage.toString();
    }

    public static void main(String[] args) {
        // Оригинальный алфавит и подстановочный алфавит
        String originalAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String substitutionAlphabet = "qwertyuiopasdfghjklzxcvbnm";

        // Создание таблиц подстановки
        createSubstitutionMap(originalAlphabet, substitutionAlphabet);

        // Пример сообщения для шифрования
        String message = "hello world";
        System.out.println("Original message: " + message);

        // Шифрование
        String encryptedMessage = encrypt(message);
        System.out.println("Encrypted message: " + encryptedMessage);

        // Дешифрование
        String decryptedMessage = decrypt(encryptedMessage);
        System.out.println("Decrypted message: " + decryptedMessage);
    }
}
