package Kursovaya;

public class Hash {

    public static String alphabet = "АБВГДЕËЖЗИЙКЛМНОПРСТУФХЦЧШЩЬЫЪЭЮЯ";

    public static void main(String[] args) {
        String input = "ЗАЦЕПИН";
        int n = 1189;
        int h = 9;

        System.out.println("-------------------------");
        System.out.println(String.format("%-25s%s", "Исходная строка:", input));
        System.out.println(String.format("%-25s%s", "n:", n));
        System.out.println(String.format("%-25s%s", "H_0:", h));
        System.out.println("-------------------------");
        int[] nums = string2nums(input);
        System.out.print(String.format("%-25s", "Исходная строка (числа): "));
        for(int i = 0; i < 7; i++){
            System.out.print(nums[i] +" ");
        }
        System.out.println();
        
        System.out.println("-------------------------");
        System.out.println("Ответ:");
        // System.out.println("nums[i] = " + nums[0] + ", h" + 0 + " = " + h);
        int hash = hash_find(nums, 0, h, n);
        System.out.println(String.format("%-25s%s", "Хэш строки:", hash));


    }

    public static int[] string2nums(String str) {
        int[] nums = new int[7];

        for (int i = 0; i < str.length(); i++) {
            nums[i] = alphabet.indexOf(str.charAt(i)) + 1;

        }

        return nums;
    }

    public static int hash_find(int[] nums, int i, int h, int n){
        if (i  == 6){
            h = ((int) Math.pow(h + nums[i],2)) % n;
            // System.out.println("nums[i] = " + nums[i] + ", h" + i + " = " + h);
            return h;
        }else{
            h = ((int) Math.pow(h + nums[i],2)) % n;
            i += 1;
            // System.out.println("nums[i] = " + nums[i] + ", h" + i + " = " + h);
            return hash_find(nums, i, h, n);
        }
    }
}