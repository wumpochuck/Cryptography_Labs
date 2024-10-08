package Kursach;

import java.math.BigInteger;
import java.util.Scanner;

public class GOST28147_89 {

    public static int[][] matrixOfNums = {
            { 1, 13, 4, 6, 7, 5, 14, 4 },
            { 15, 11, 11, 12, 13, 8, 11, 10 },
            { 13, 4, 10, 7, 10, 1, 4, 9 },
            { 0, 1, 0, 1, 1, 13, 12, 2 },
            { 5, 3, 7, 5, 0, 10, 6, 13 },
            { 7, 15, 2, 15, 8, 3, 13, 8 },
            { 10, 5, 1, 13, 9, 4, 15, 0 },
            { 4, 9, 13, 8, 15, 2, 10, 14 },
            { 9, 0, 3, 4, 14, 14, 2, 6 },
            { 2, 10, 6, 10, 4, 15, 3, 11 },
            { 3, 14, 8, 9, 6, 12, 8, 1 },
            { 14, 7, 5, 14, 12, 7, 1, 12 },
            { 6, 6, 9, 0, 11, 6, 0, 7 },
            { 8, 2, 15, 11, 5, 9, 5, 5 },
            { 12, 12, 14, 2, 3, 11, 9, 3 } };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "ЗАЦЕПИНМ";
        String key = "АНДР";

        System.out.println("-------------------------");
        System.out.println(String.format("%-25s%s", "Исходная строка (L0 R0):", input));
        System.out.println(String.format("%-25s%s", "Исходный ключ (X0):", key));
        System.out.println("-------------------------");

        String step1 = string2binary(input);
        System.out.println(String.format("%-25s%s", "1) Двоичная запись слова", step1));

        String step1_key = string2binary(key);
        System.out.println(String.format("%-25s%s", "2) Двоичная запись ключа", step1_key));

        String step2 = sumStrings(step1.substring(32, 64), step1_key);
        System.out.println(String.format("%-25s%s", "3) Сумма записей R0 X0", step2));

        int[] step3 = binary2Nums(step2);
        System.out.print(String.format("%-25s", "4) Разбиение по 4-кам"));
        for (int i = 0; i < 8; i++) {
            System.out.print(step3[i] + " ");
        }
        System.out.println();

        int[] step4 = numsFromArray(step3);
        System.out.print(String.format("%-25s", "5) Замена по таблице"));
        for (int i = 0; i < 8; i++) {
            System.out.print(step4[i] + " ");
        }
        System.out.println();

        String step5 = arr2Binary(step4);
        System.out.println(String.format("%-25s%s", "6) Двоичная запись замен", step5));

        String step6 = rot11(step5);
        System.out.println(String.format("%-25s%s", "7) ROT11 записи", step6));

        String step7 = xor(step6, step1.substring(0, 32));
        System.out.println(String.format("%-25s%s", "8) XOR записи", step7));

        System.out.println("-------------------------");
        System.out.println("Ответ:");
        System.out.println("(R1) = " + step7);
        System.out.println("(L1) = " + step1.substring(32, 64));
        System.out.println("(L1) = (R0)");
        System.out.println("-------------------------");

        scanner.close();
    }

    public static String string2binary(String str) {
        String step1 = "";

        for (int i = 0; i < str.length(); i++) {
            String char2 = Integer.toBinaryString(((int) str.charAt(i) - (int) 'А') ^ 192);
            step1 += char2;
        }

        return step1;
    }

    public static String sumStrings(String s1, String s2) {
        String step2 = "";
        BigInteger num1 = new BigInteger(s1, 2);
        BigInteger num2 = new BigInteger(s2, 2);
        step2 = num1.add(num2).toString(2);
        step2 = step2.substring(step2.length() - 32, step2.length());

        return step2;
    }

    public static int[] binary2Nums(String str) {
        int[] step3 = { 0, 1, 2, 3, 4, 5, 6, 7 };
        for (int i = 0; i < str.length(); i += 4) {
            String substr = str.substring(i, i + 4);
            step3[i / 4] = Integer.parseInt(substr, 2);
        }

        return step3;
    }

    public static int[] numsFromArray(int[] arr) {
        int[] step4 = { 0, 1, 2, 3, 4, 5, 6, 7 };
        for (int i = 0; i < 8; i++) {
            step4[i] = matrixOfNums[arr[i]][i];
        }

        return step4;
    }

    public static String arr2Binary(int[] arr) {
        String step5 = "";

        for (int i = 0; i < 8; i++) {
            String temp = Integer.toBinaryString(arr[i]);
            String str = "";
            for (int j = 0; j < 4 - temp.length(); j++) {
                str += "0";
            }
            step5 += str + temp;
        }

        return step5;
    }

    public static String rot11(String str) {
        String step6 = "";
        step6 += str.substring(11, str.length());
        step6 += str.substring(0, 11);

        return step6;
    }

    public static String xor(String s1, String s2) {
        String step7 = "";
        BigInteger num1 = new BigInteger(s1, 2);
        BigInteger num2 = new BigInteger(s2, 2);
        step7 = (num1.xor(num2)).toString(2);

        return step7;
    }

}