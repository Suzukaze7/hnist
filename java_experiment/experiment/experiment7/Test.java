package experiment.experiment7;

import experiment.experiment7.part1.*;
import experiment.experiment7.part2.ElectricCalculator;

public class Test {
    public static void main(String[] args) {
        Geometric geometric = new Circle("red", 3);
        System.out.printf("图形的面积为：%.2f\n", geometric.findArea());
        System.out.printf("图形的周长为为：%.2f\n", geometric.findPerimeter());

        geometric = new Rectangle("white", 5, 6);
        System.out.printf("图形的面积为：%.2f\n", geometric.findArea());
        System.out.printf("图形的周长为为：%.2f\n", geometric.findPerimeter());
        System.out.println("----------------------");

        ElectricCalculator electricCalculator = new ElectricCalculator();

        double x = 5.5, y = 1.1;
        System.out.println(x + " 和 " + y + " 的和为：" + electricCalculator.add(x, y));
        System.out.println(x + " 和 " + y + " 的差为：" + electricCalculator.reduce(x, y));
        System.out.println(x + " 和 " + y + " 的积为：" + electricCalculator.multiple(x, y));
        System.out.println(x + " 和 " + y + " 的除为：" + electricCalculator.add(x, y));

        int a = 5, b = 3;
        System.out.println(a + " 和 " + b + " 的模为：" + electricCalculator.residue(a, b));
        System.out.println(x + " 和 " + b + " 的幂为：" + electricCalculator.factorial(x, b));
    }
}
