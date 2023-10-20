package experiment;

import javax.swing.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Experiment3 {
     public Experiment3() {
        System.out.print("实验3：\n");
    }

    public void part1() {
        JOptionPane.showMessageDialog(null,"利用消息框输入实现三个数的排序");

        int number[] = new int[3];
        for (int i = 0; i <= 2;) {
            String numberInString = JOptionPane.showInputDialog("请输入第" + (i + 1) + "个int范围的整数");

            try {
                number[i] = Integer.parseInt(numberInString);
                i++;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "输入的不是数字或数字过大，请重新输入");
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "无输入数字，请重新输入");
            }
        }

        for (int i = 0; i < 2; i++)
            for (int j = 2; j > i; j--)
                if (number[j - 1] > number[j]) {
                    int t = number[j];
                    number[j] = number[j - 1];
                    number[j - 1] = t;
                }

        JOptionPane.showMessageDialog(null, "排序后的结果为：\n" + number[0] + " " + number[1] + " " + number[2]);
    }

    public void part2() {
        System.out.print("数字转换星期：\n");

        Scanner in = new Scanner(System.in);

        System.out.print("请输入任意8以内的正整数n：");
        int n;
        while (true) {
            try {
                n = in.nextInt();
                if (n <= 0 || n >= 8)
                    throw new RuntimeException();
                break;
            } catch (InputMismatchException e) {
                System.out.print("非法输入，请重新输入任意8以内的正整数n：");
                in.next();
            } catch (RuntimeException e) {
                System.out.print("非法输入，请重新输入任意8以内的正整数n：");
            }
        }
        in.close();

        String week = "";
        switch (n) {
            case 1:
                week = "一";
                break;
            case 2:
                week = "二";
                break;
            case 3:
                week = "三";
                break;
            case 4:
                week = "四";
                break;
            case 5:
                week = "五";
                break;
            case 6:
                week = "六";
                break;
            case 7:
                week = "日";
                break;
         }
        System.out.print("今天是星期" + week + "\n\n");
    }

    private long factorial(int number) {
         long res = 1;
         for (int i = 1; i <= number; i++)
             res *= i;
         return res;
    }

    public void part3() {
         System.out.print("求三个数的阶乘和：\n");

         Scanner in = new Scanner(System.in);

         int number[] = new int[3];
         for (int i = 0; i <= 2;) {
             System.out.print("请输入第" + (i + 1) + "个20以内的整数：");

             try {
                 number[i] = in.nextInt();
                 if (number[i] < 0 || number[i] >= 20)
                     throw new RuntimeException();

                 i++;
             } catch (InputMismatchException e) {
                 System.out.print("非法输入，请重新输入\n");
                 in.next();
             } catch (RuntimeException e) {
                 System.out.print("非法输入，请重新输入\n");
             }
         }
         in.close();

         long sum = 0;
         for (int i = 0; i <= 2; i++)
             sum += factorial(number[i]);

         System.out.print("三个数的阶乘和为：" + sum + "\n\n");
    }
}
