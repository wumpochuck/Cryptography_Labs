package Kursovaya;

import java.math.BigInteger;
import java.util.Scanner;

public class RSA {

    public static String alphabet = "АБВГДЕËЖЗИЙКЛМНОПРСТУФХЦЧШЩЬЫЪЭЮЯ";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "ЗМА";
        int p = 41;
        int q = 29;

        System.out.println("-------------------------");
        System.out.println(String.format("%-25s%s", "Исходная строка:", input));
        System.out.println(String.format("%-25s%s", "p:", p));
        System.out.println(String.format("%-25s%s", "q:", q));
        System.out.println("-------------------------");

        int n = p * q;
        int fi_n = ((p - 1) * (q - 1));
        System.out.println(String.format("%-25s%s", "n:", n));
        System.out.println(String.format("%-25s%s", "fi(n):", fi_n));

        int d = find_d(fi_n);
        System.out.println(String.format("%-25s%s", "d:", d));

        int e = find_e(d, fi_n);
        System.out.println(String.format("%-25s%s", "e:", e));

        System.out.println(String.format("%-25s%3s %4s", "Открытый ключ (e,n):", e, n));
        System.out.println(String.format("%-25s%3s %4s", "Закрытый ключ (d,n):", d, n));

        System.out.println("-------------------------");
        BigInteger input_nums[] = string2nums(input);
        System.out.print(String.format("%-25s", "Исходная строка (числа):"));
        for (int i = 0; i < 3; i++) {
            System.out.print(" " + input_nums[i]);
        }
        System.out.println();
        BigInteger C[] = encrypt(input_nums, e, n);
        System.out.print(String.format("%-25s", "Зашифрованная строка:"));
        for (int i = 0; i < 3; i++) {
            System.out.print(" " + C[i]);
        }
        System.out.println();
        BigInteger M[] = decrypt(C, d, n);
        System.out.print(String.format("%-25s", "Расшифрованная строка:"));
        for (int i = 0; i < 3; i++) {
            System.out.print(" " + M[i]);
        }
        System.out.println();

        scanner.close();
    }

    public static int find_d(int fi_n) {
        int d = 2;
        boolean flag = true;
        while (flag) {
            int count = 0;
            if (fi_n % d == 0) {
                count++;
            } else {
                for (int i = 2; i <= d; i++) {
                    if (d % i == 0 && fi_n % i == 0) {
                        count++;
                    }
                }
            }
            if (count == 0) {
                flag = false;
            } else {
                d++;
            }
        }

        return d;
    }

    public static int find_e(int d, int fi_n) {
        int e = -1;
        // d * e = 1mod(fi_n)
        // 3 * e = 1mod(1120)
        // e = (1120 * y + 1) / 3

        int y = 1;
        while ((1120 * y + 1) % 3 != 0) {
            y += 1;
        }
        e = ((1120 * y + 1) / 3);

        return e;
    }

    public static BigInteger[] string2nums(String str) {
        BigInteger[] nums = new BigInteger[3];

        for (int i = 0; i < str.length(); i++) {
            nums[i] = BigInteger.valueOf(alphabet.indexOf(str.charAt(i)) + 1);

        }

        return nums;
    }

    public static BigInteger[] encrypt(BigInteger[] arr, int e, int n) {
        BigInteger[] C = new BigInteger[3];

        for (int i = 0; i < 3; i++) {
            BigInteger temp = arr[i];
            temp = temp.pow(e);
            temp = temp.mod(BigInteger.valueOf(n));
            C[i] = temp;
        }

        return C;
    }

    public static BigInteger[] decrypt(BigInteger[] arr, int d, int n) {
        BigInteger[] M = new BigInteger[3];

        for (int i = 0; i < 3; i++) {
            BigInteger temp = arr[i];
            temp = temp.pow(d);
            temp = temp.mod(BigInteger.valueOf(n));
            M[i] = temp;
        }

        return M;
    }

}