package experiment.experiment7.part1;

public class Circle extends Geometric {
    static private final double PI = 3.1415926;
    private double radius;

    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double findArea() {
        return PI * radius * radius;
    }

    @Override
    public double findPerimeter() {
        return 2 * PI * radius;
    }
}
