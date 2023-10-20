package experiment.experiment9;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class part1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int member[] = new int[4];
        for (int i = 0; i < 4;) {
            System.out.println("请输入第" + (i + 1) + "位会员的会员号：");
            member[i] = in.nextInt();

            if (member[i] <= 999 || member[i] >= 10000)
                System.out.println("会员号有误，请重新输入");
            else
                i++;
        }

//        int randomNumber = (int)Math.random() * 10;

        Random random = new Random();
        int randomNumber = random.nextInt(9);
        System.out.println("本次幸运号码为："  + randomNumber);

        ArrayList<Integer> luckyMember = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (member[i] / 100 % 10 == randomNumber)
                luckyMember.add(member[i]);
        }

        if (luckyMember.isEmpty())
            System.out.println("抱歉，本次抽奖没有幸运会员-_-");
        else {
            System.out.println("本次抽奖的幸运会员为：");
            for (int i = 0; i < luckyMember.size(); i++)
                System.out.print(luckyMember + " ");
        }
    }
}
