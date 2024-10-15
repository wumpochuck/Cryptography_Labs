package Kursovaya;

import java.math.BigInteger;

public class Digital_Sign {

    public static void main(String[] args) {
        int input = 951;
        int p = 47;
        int q = 41;

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
        
        System.out.println("-------------------------");
        System.out.println("Ответ:");
        BigInteger s = BigInteger.valueOf(input);
        s = s.pow(d);
        s = s.mod(BigInteger.valueOf(n));
        System.out.println(String.format("%-25s%s", "s:", s));
        
        BigInteger H = s;
        H = H.pow(e);
        H = H.mod(BigInteger.valueOf(n));
        System.out.println(String.format("%-25s%s", "H:", H));


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

        int y = 1;
        while ((fi_n * y + 1) % 3 != 0) {
            y += 1;
        }
        e = ((fi_n * y + 1) / 3);

        return e;
    }
}