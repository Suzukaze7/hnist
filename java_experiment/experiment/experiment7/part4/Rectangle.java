package experiment.experiment7.part4;

public class Rectangle extends Shape {
    public double length, height;

    @Override
    public double area() {
        return length * height;
    }

    @Override
    public double perimeter() {
        return (length + height) * 2;
    }
}
