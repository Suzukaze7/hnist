package finaltest.remove;

import java.io.*;
import java.util.ArrayList;

public class SignInTable extends Thread implements SignInSearchable {
    private final String tablePath = tableDirPath + "/signin.txt";
    private final ArrayList<SignIn> table = new ArrayList<>();

    public SignInTable() {
        start();
    }

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tablePath));

            while (true)
                try {
                    table.add((SignIn) ois.readObject());
                } catch (EOFException e) {
                    break;
                }

            ois.close();
        } catch (Exception e) {
        }
    }

    @Override
    public void save() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tablePath));
            for (SignIn signIn : table)
                oos.writeObject(signIn);
            oos.close();
        } catch (IOException e) {
        }
    }

    @Override
    public ArrayList<SignIn> searchAll() {
        return new ArrayList<SignIn>(table);
    }

    @Override
    public ArrayList<SignIn> search(int week, int day, int time, String courseName, String studentName) {
        ArrayList<SignIn> res = new ArrayList<>();
        for (SignIn signIn : table) {
            if (week != 0 && week != signIn.getWeek())
                continue;
            if (day != 0 && day != signIn.getDay())
                continue;
            if (time != 0 && time != signIn.getTime())
                continue;
            if (courseName != null && !courseName.equals(signIn.getCourseName()))
                continue;
            if (studentName != null && !studentName.equals(signIn.getStudentName()))
                continue;

            res.add(signIn);
        }

        return res;
    }
}