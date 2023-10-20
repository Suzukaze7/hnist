package tool;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import object.Course;
import object.User;

public class Test {

    public static void main(String[] args) throws Exception {
        addUser();

        Controller controller = new Controller();
        controller.mainMenu();
    }

    public static void addCourse() throws FileNotFoundException, IOException {
        ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("D:\\java\\study\\Experiment\\finaltest\\src\\table\\storage\\course.txt"));

        oos.writeObject(new Course(4, 1, "方欣", "Java"));
        oos.close();
    }

    public static void addUser() throws FileNotFoundException, IOException {
        ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("D:\\java\\study\\Experiment\\finaltest\\src\\table\\storage\\user.txt"));

        oos.writeObject(new User("14202401820", "362573", "jxy", 20, true, true));
        oos.writeObject(new User("admin", "123456", "Jerry", 16, true, false));
        oos.close();
    }
}