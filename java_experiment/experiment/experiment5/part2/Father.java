package experiment.experiment5.part2;

public class Father {
    private String name = "zhangjun";

    public class Child {
        public String introFather() {
            return Father.this.name;
        }
    }
}
