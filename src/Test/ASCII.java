package Test;

public class ASCII {

    public static void main(String[] args) {
        for(int i = 0; i < 2048; i++){
            System.out.format("Index = %d, ASCII symbol = %3c -----  %d\n", i, (char)i, (int)((char)i));
        }

        System.out.println();
        System.out.println("Ë = " + (int)'Ë');
    }
}