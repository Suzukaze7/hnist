package experiment.experiment7.part2;

public class ElectricCalculator implements Calable {
    @Override
    public double add(double x, double y) {
        return x + y;
    }

    @Override
    public double reduce(double x, double y) {
        return x - y;
    }

    @Override
    public double multiple(double x, double y) {
        return x * y;
    }

    @Override
    public double divide(double x, double y) {
        return x / y;
    }

    @Override
    public int residue(int x, int y) {
        return x % y;
    }

    @Override
    public double factorial(double x, int y) {
        double res = 1;
        for (int i = 1; i <= y; i++)
            res *= x;
        return res;
    }
}
