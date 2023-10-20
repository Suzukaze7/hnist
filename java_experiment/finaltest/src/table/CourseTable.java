package table;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeSet;

import object.Course;
import table.search.CourseSearchable;

public class CourseTable extends Thread implements CourseSearchable {

    private final String tablePath = tableDirPath + "course.txt";
    private final TreeSet<Course> table = new TreeSet<>();

    public CourseTable() {
        start();
    }

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tablePath));

            while (true)
                try {
                    table.add((Course) ois.readObject());
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
            for (Course course : table)
                oos.writeObject(course);
            oos.close();
        } catch (IOException e) {
        }
    }

    @Override
    public ArrayList<Course> searchAll() {
        return new ArrayList<Course>(table);
    }

    @Override
    public ArrayList<Course> searchByDay(int day) {
        ArrayList<Course> res = new ArrayList<>();
        for (Course course : table)
            if (course.getDay() == day)
                res.add(course);
        return res;
    }

    @Override
    public ArrayList<Course> searchByCourseName(String courseName) {
        ArrayList<Course> res = new ArrayList<>();
        for (Course course : table)
            if (course.getCourseName().equals(courseName))
                res.add(course);
        return res;
    }

    @Override
    public ArrayList<Course> searchByTeacherName(String teacherName) {
        ArrayList<Course> res = new ArrayList<>();
        for (Course course : table)
            if (course.getTeacherName().equals(teacherName))
                res.add(course);
        return res;
    }

    @Override
    public ArrayList<Course> searchByMulInfo(int day, int time, String courseName, String teacherName) {
        ArrayList<Course> res = new ArrayList<>();
        for (Course course : table) {
            if (day != 0 && day != course.getDay())
                continue;
            if (time != 0 && time != course.getTime())
                continue;
            if (courseName != null && courseName.equals(courseName))
                continue;
            if (teacherName != null && teacherName.equals(teacherName))
                continue;

            res.add(course);
        }

        return res;
    }

    @Override
    public void removeAll(ArrayList<Course> courses) {
        table.removeAll(courses);
        save();
    }

    @Override
    public void add(Course course) {
        table.add(course);
        save();
    }
}