package experiment.experiment11;

import java.io.*;

public class WriteTest {
    public static void main(String[] args) throws IOException {
        final String dirPath = "D:\\java\\study\\Experiment\\experiment\\experiment11\\test.txt";

        Student s1 = new Student("Tom", "111", 15, "catchMouse", true);
        Student s2 = new Student("Jerry", "222", 13, "hideAndSeek", false);

        File file = new File(dirPath);
        if (!file.exists())
            file.createNewFile();

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(s1);
        oos.writeObject(s2);
        oos.close();
    }
}
