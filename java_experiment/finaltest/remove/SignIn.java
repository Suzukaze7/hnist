package finaltest.remove;

import java.io.Serializable;

public class SignIn implements Serializable, Comparable<SignIn> {
    private int week;
    private int day;
    private int time;
    private String courseName;
    private String studentName;

    public SignIn(int week, int day, int time, String courseName, String studentName) {
        this.week = week;
        this.day = day;
        this.time = time;
        this.courseName = courseName;
        this.studentName = studentName;
    }

    public int getWeek() {
        return week;
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

    public String getStudentName() {
        return studentName;
    }

    @Override
    public int compareTo(SignIn obj) {
        if (week != obj.week)
            return week - obj.week;
        if (day != obj.day)
            return day - obj.day;
        return time - obj.time;
    }

    @Override
    public String toString() {
        return week + day + "的第" + time + "节课(" + courseName + ") " + studentName + "缺勤了";
    }

}
