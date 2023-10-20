package experiment.experiment5;

import experiment.experiment5.part1.Student;
import experiment.experiment5.part2.Father;

public class Test {
    public static void main(String[] args) {
        Student student = new Student();
        System.out.println(student.getName() + " " + student.getScore());
        student.setName("Jerry");
        student.setScore(88);
        System.out.println(student.getName() + " " + student.getScore());

        System.out.println();

        Father.Child child = new Father().new Child();
        System.out.println(child.introFather());
    }
}
