package experiment.experiment7.part1;

abstract public class Geometric {
    protected String color;

    public Geometric(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    abstract public double findArea();
    abstract public double findPerimeter();
}