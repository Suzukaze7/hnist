package experiment;

import java.util.Scanner;

public class Experiment2 {
	public Experiment2() {
		System.out.print("实验2：\n");
	}
	
	public void part1() {
		System.out.print("输出100-1000内的水仙花数：\n");
		
		for (int number = 100; number <= 1000; number++) {
			int tmp = number, sum = 0;

			while (tmp != 0) {
				int k = tmp % 10;
				sum += k * k * k;
				tmp /= 10;
			}
			
			if (sum == number)
				System.out.print(number + " ");
		}
		System.out.print("\n\n");
	}
	
	public void part2() {
		System.out.print("三个数的升序排序：\n");
		
		System.out.print("请输入三个整数：");
		Scanner in = new Scanner(System.in);
		int number1, number2, number3;
		number1 = in.nextInt();
		number2 = in.nextInt();
		number3 = in.nextInt();
		
		if (number1 > number2) {
			int t = number1;
			number1 = number2;
			number2 = t;
		}
		
		if (number2 > number3) {
			int t = number2;
			number2 = number3;
			number3 = t;
		}
		
		if (number1 > number2) {
			int t = number1;
			number1 = number2;
			number2 = t;
		}
		
		System.out.print(number1 + " " + number2 + " " + number3 + "\n\n");
		in.close();
	}
	
	public void part3() {
		System.out.print("输出1-100内的质数：\n");
		
		for (int number = 2; number <= 100; number++) {
			boolean isPrime = true;
			
			for (int i = 2; i <= number / i; i++)
				if (number % i == 0) {
					isPrime = false;
					break;
				}
			
			if (isPrime)
				System.out.print(number + " ");
		}
		
		System.out.print("\n\n");
	}
	
	public void part4() {
		System.out.print("输出菱形：\n");
		
		System.out.print("请输入一个正整数n：");
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		while (n <= 0) {
			System.out.print("非法数字，请重新输入正整数n：");
			n = in.nextInt();
		}

		for (int blank = n - 1, star = 1; blank >= 0; blank--, star += 2) {
			for (int i = 1; i <= blank; i++)
				System.out.print(' ');
			for (int i = 1; i <= star; i++)
				System.out.print('*');
			System.out.println();
		}
		for (int blank = 1, star = 2 * n - 3; blank <= n - 1; blank++, star -= 2) {
			for (int i = 1; i <= blank; i++)
				System.out.print(' ');
			for (int i = 1; i <= star; i++)
				System.out.print('*');
			System.out.print("\n");
		}
		
		System.out.print("\n");
		in.close();
	}
}
