package experiment.experiment11;

import java.io.*;

public class ReadTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String dirPath = "D:\\java\\study\\Experiment\\experiment\\experiment11\\test.txt";

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dirPath));
        Student s1 = (Student) ois.readObject();
        Student s2 = (Student) ois.readObject();
        ois.close();

        System.out.println(s1);
        System.out.println(s2);
    }
}
