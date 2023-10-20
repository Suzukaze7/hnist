package experiment;

import java.util.Scanner;

public class Experiment4 {
    public Experiment4() {
        System.out.print("实验4：\n");
    }

    private boolean check(int x) {
        if (0 <= x && x <= 100)
            return true;
        else {
            System.out.print("非法输入，请重新输入\n");
            return false;
        }
    }

    private int[] scan(int n) {
        Scanner in = new Scanner(System.in);

        int number[] = new int[n];
        for (int i = 0; i < n;) {
            System.out.print("请输入第" + (i + 1) + "个100以内的整数：");
            number[i] = in.nextInt();

            if (check(number[i]))
                i++;
        }

        return number;
    }

    private void sort(int number[], int size) {
        for (int i = 0; i < size; i++)
            for (int j = 9; j > i; j--)
                if (number[j] < number[j - 1])
                {
                    int t = number[j];
                    number[j] = number[j - 1];
                    number[j - 1] = t;
                }
    }

    private void print(int number[], int size) {
        for (int i = 0; i < size; i++)
            System.out.print(number[i] + " ");
    }

    public void part1() {
        System.out.print("求5个100以内的整数数的和及平均数：\n");
        final int SIZE = 5;

        int number[] = scan(SIZE);

        int sum = 0;
        for (int i = 0; i < SIZE; i++)
            sum += number[i];
        double avg = (double) sum / SIZE;

        System.out.printf("和为%d，平均值为%.2f\n\n", sum, avg);
    }

    public void part2() {
        System.out.print("将10个100以内的整数按从小到大排序：\n");
        final int SIZE = 10;

        Scanner in = new Scanner(System.in);

        int number[] = scan(SIZE);
        sort(number, SIZE);

        System.out.print("从小到大排序为：");
        print(number, SIZE);
        System.out.print("\n\n");
    }

    public void part3() {
        System.out.print("将10个100以内的整数去重：\n");
        final int SIZE = 10;

        int number[] = scan(SIZE);
        sort(number, SIZE);

        int uniqueNumber[] = new int[SIZE], idx = 0;
        uniqueNumber[idx++] = number[0];
        for (int i = 1; i < SIZE; i++)
            if (number[i] != number[i - 1])
                uniqueNumber[idx++] = number[i];

        System.out.print("去重后的数组为：");
        print(number, idx);
        System.out.print("\n\n");
    }

    public void part4() {
        System.out.print("去除100以内的数组成的数组的0：\n");

        System.out.print("请输入数组大小size：");

        Scanner in = new Scanner(System.in);
        int size = in.nextInt();

        int number[] = scan(size);
        int eraseZeroNumber[] = new int[size], idx = 0;
        for (int i = 0; i < size; i++)
            if (number[i] != 0)
                eraseZeroNumber[idx++] = number[i];

        System.out.print("去除0之后的数组为：");
        print(eraseZeroNumber, idx);
        System.out.print("\n\n");
    }

    public void part5() {
        System.out.print("将两个数组合并为一个数组：\n");

        Scanner in = new Scanner(System.in);

        int size1, size2;
        while (true) {
            System.out.print("请输入第一个数组的大小size1：");
            size1 = in.nextInt();

            if (1 <= size1 && size1 <= 10)
                break;

            System.out.print("非法输入，请重新输入\n");
        }
        System.out.print("请输入第一个数组的值：\n");
        int number1[] = scan(size1);
        System.out.print("\n");

        while (true) {
            System.out.print("请输入第二个数组的大小size1：");
            size2 = in.nextInt();

            if (1 <= size2 && size2 <= 10)
                break;

            System.out.print("非法输入，请重新输入\n");
        }
        System.out.print("请输入第二个数组的值：\n");
        int number2[] = scan(size2);

        int newSize = size1 + size2, idx = 0;
        int newNumber[] = new int[newSize];

        for (int i = 0; i < size1; i++)
            newNumber[idx++] = number1[i];
        for (int i = 0; i < size2; i++)
            newNumber[idx++] = number2[i];

        System.out.print("合并后的数组为：");
        print(newNumber, newSize);
        System.out.print("\n\n");
    }
}
