package table.search;

import java.util.ArrayList;

import object.Course;

public interface CourseSearchable extends Searchable {

    ArrayList<Course> searchByDay(int day);

    ArrayList<Course> searchByCourseName(String courseName);

    ArrayList<Course> searchByTeacherName(String teacherName);

    ArrayList<Course> searchByMulInfo(int day, int time, String courseName, String teacherName);

    void removeAll(ArrayList<Course> courses);

    void add(Course course);
}