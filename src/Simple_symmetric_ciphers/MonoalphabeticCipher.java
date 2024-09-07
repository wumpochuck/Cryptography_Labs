/*
 * Задание 2: Придумайте свой простой подстановочный шифр
 * (моноалфавитный шифр), реализуйте его на любом языке
 * программирования зашифруйте с помощью вашего шифра произвольное
 * сообщение, после чего дешифруйте его.
 */

// ROT4 Cipher for rus & eng words

package Simple_symmetric_ciphers;

import java.util.Scanner;

public class MonoalphabeticCipher {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String inputStr = "";
        int choice = 0;

        boolean flag = true;
        while (flag) {
            System.out.println("\nMenu\n1 - encrypt string" +
                    "\n2 - decrypt string" +
                    "\n0 - exit\n");

            choice = console.nextInt();
            console.skip("\n");
            switch (choice) {
                case 1:
                    System.out.print("enter string to encrypt: ");
                    inputStr = console.nextLine();
                    System.out.println("entered str:   \'" + inputStr + "\'");
                    String encryptedStr = doRotation(inputStr, 4);
                    System.out.println("encrypted str: \'" + encryptedStr + "\'");
                    break;

                case 2:
                    System.out.print("enter string to decrypt: ");
                    inputStr = console.nextLine();
                    System.out.println("entered str:   \'" + inputStr + "\'");
                    String decryptedStr = doRotation(inputStr, -4);
                    System.out.println("decrypted str: \'" + decryptedStr + "\'");
                    break;

                case 0:
                    flag = false;
                    break;

                default:

                    System.out.println("wrong input");
                    break;
            }
        }

        console.close();
    }

    public static String doRotation(String inputStr, int rot) {
        rot %= 26;
        if (rot < 0) {
            rot = 26 + rot;
        }

        String outputStr = "";
        for (int i = 0; i < inputStr.length(); i++) {
            char symbol = inputStr.charAt(i);
            if (symbol >= 'A' && symbol <= 'z') {
                symbol = (char) ((((int) symbol - 97 + rot) % 26) + 97);
            } else if (symbol >= 'a' && symbol <= 'z') {
                symbol = (char) ((((int) symbol - 65 + rot) % 26) + 65);
            }
            outputStr += symbol;
        }

        return outputStr;
    }
}