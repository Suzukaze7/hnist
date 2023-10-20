package experiment.experiment8;

import java.util.ArrayList;

public class part2 {
    public static void main(String[] args) {
        try {
            int a = 1 / 0;
        }
        catch (ArithmeticException e) {
            System.out.println("抛出了 " + e + " 异常并捕获");
        }
    }
}
