package experiment.experiment9;

import java.util.Random;

public class part4 {
    public static int getNumber() {
        Random random = new Random();

        int res = random.nextInt(9) + 1;
        res = res * 10 + random.nextInt(10);
        res = res * 10 + random.nextInt(10);

        return res;
    }

    public static void main(String[] args) {
        int num1 = getNumber(), num2 = getNumber(), res = 0;

        System.out.print(num1 +  " 与 " + num2 + " 的");
        switch (new Random().nextInt(5)) {
            case 0:
                System.out.print("和为");
                res = num1 + num2;
                break;
            case 1:
                System.out.print("差为");
                res = num1 - num2;
                break;
            case 2:
                System.out.print("积为");
                res = num1 * num2;
                break;
            case 3:
                System.out.print("除为");
                res = num1 / num2;
                break;
            case 4:
                System.out.print("模为");
                res = num1 % num2;
                break;
        }
        System.out.print(" " + res);
    }
}
