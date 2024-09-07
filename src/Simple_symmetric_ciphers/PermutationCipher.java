/*
 * Задание 1: Придумайте свой перестановочный шифр, реализуйте его на
 * любом языке программирования, зашифруйте с помощью вашего шифра
 * произвольное сообщение, после чего дешифруйте его. Опишите достоинства
 * и недостатки шифра.
 */

package Simple_symmetric_ciphers;

import java.util.Scanner;

public class PermutationCipher {

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
                    System.out.print("enter string to encrypt (entered spaces will be deleted): ");
                    inputStr = console.nextLine();
                    inputStr = inputStr.replace(" ", "");
                    System.out.println("entered str:   \'" + inputStr + "\'");
                    String encryptedStr = encrypt(inputStr);
                    System.out.println("encrypttd str: \'" + encryptedStr + "\'");
                    break;

                case 2:
                    System.out.print("enter string to decrypt (spaces will deleted): ");
                    inputStr = console.nextLine();
                    System.out.println("entered str:   \'" + inputStr + "\'");
                    String decryptedStr = decrypt(inputStr);
                    decryptedStr = decryptedStr.replace(" ", "");
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

    public static String encrypt(String inputStr) {
        String encryptedStr = "";

        int n = getStepSize(inputStr);
        int addSize = (n * n) - inputStr.length();
        if (addSize != 0) {
            System.out.println("some spaces was added to str for better decrypting");
            for (int i = 0; i < addSize; i++) {
                inputStr += " ";
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (j * n + i < inputStr.length()) {
                    encryptedStr += inputStr.charAt((j * n + i));
                }
            }
        }

        return encryptedStr;
    }

    public static String decrypt(String inputStr) {
        String decryptedStr = "";

        int n = getStepSize(inputStr);

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (j * n + i < inputStr.length()) {
                    decryptedStr += inputStr.charAt((j * n + i));
                }
            }
        }

        return decryptedStr;
    }

    public static int getStepSize(String str) {
        int size = 0;
        int strLen = str.length();

        while (size * size < strLen)
            size++;

        return size;
    }

}
