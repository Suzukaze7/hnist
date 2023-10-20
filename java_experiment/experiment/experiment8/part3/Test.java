package experiment.experiment8.part3;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);

            int choose = in.nextInt();
            if (choose == 1)
                throw new AaaException();
            else
                throw new BbbException();
        }
        catch (AaaException e) {
            System.out.println("抛出了 " + e + " 异常并捕获");
        }
        catch (BbbException e) {
            System.out.println("抛出了 " + e + " 异常并捕获");
        }



    }
}
