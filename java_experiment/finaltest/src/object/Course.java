package object;

import java.io.Serializable;

public class Course implements Serializable, Comparable<Course> {
    private static final String weekDay[] = { null, "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日" };

    private int day;
    private int time;
    private String courseName;
    private String teacherName;

    public Course(int day, int time, String courseName, String teacherName) {
        this.day = day;
        this.time = time;
        this.courseName = courseName;
        this.teacherName = teacherName;
    }

    public int getDay() {
        return day;
    }

    public int getTime() {
        return time;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    @Override
    public int compareTo(Course obj) {
        if (day != obj.day)
            return day - obj.day;
        return time - obj.time;
    }

    @Override
    public String toString() {
        return weekDay[day] + "第" + time + "节 " + teacherName + "老师的" + courseName + "课";
    }
}