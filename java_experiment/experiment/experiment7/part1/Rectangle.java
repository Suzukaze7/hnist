package experiment.experiment7.part1;

public class Rectangle extends Geometric {
    private double length;
    private double width;

    public Rectangle(String color, double length, double width) {
        super(color);
        this.length = length;
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public double findArea() {
        return length * width;
    }

    @Override
    public double findPerimeter() {
        return 2 * (length + width);
    }
}
