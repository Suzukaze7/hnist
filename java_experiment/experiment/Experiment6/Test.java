package experiment.Experiment6;

import experiment.Experiment6.part1.Dog;
import experiment.Experiment6.part2.StudentList;

public class Test {
    public static void main(String[] args) {
        Dog dog1 = new Dog("三千", "柴犬", 3, true);
        dog1.run();
        dog1.bark();
        System.out.println("--------------------------");
        Dog dog2 = new Dog("史努比", "柯基", 2, false);
        dog2.run();
        dog2.bark();

        System.out.println();

        StudentList studentList = new StudentList();
        studentList.addStudent(1, 16, 76);
        studentList.addStudent(2, 16, 54);
        studentList.addStudent(3, 16, 89);
        studentList.addStudent(4, 16, 96);
        studentList.addStudent(5, 16, 23);
        studentList.addStudent(6, 16, 77);
        studentList.addStudent(7, 16, 48);
        System.out.printf("平均成绩为：%.2f\n", studentList.average());
    }
}
