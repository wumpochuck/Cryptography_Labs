package Test;

public class ASCII {

    public static void main(String[] args) {
        for(int i = 0; i < 1024; i++){
            System.out.format("Index = %d, ASCII symbol = %3c -----  %d\n", i, (char)i, (int)((char)i));
        }
    }
}