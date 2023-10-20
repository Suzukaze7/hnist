package tool;

import java.util.ArrayList;
import java.util.Scanner;

import execption.*;
import object.*;
import table.*;

public class Controller {
    private Scanner in = new Scanner(System.in);

    private CourseTable courseTable = new CourseTable();
    private UserTable userTable = new UserTable();
    private User user;

    public void login() {
        while (true) {
            clearScreen();
            System.out.println("=============================");
            System.out.println("======== 课程管理系统 ========");
            System.out.println("=============================");

            boolean isStudent = (inputInfo(1, 2, "请选择 学生/教师 端登陆(1为学生，2为教师)：") == 1 ? true : false);
            String userID = inputInfo("请输入账户名：", false);
            String password = inputInfo("请输入密码：", false);

            User user = userTable.searchByUserId(userID);
            if (user != null && user.getPassword().equals(password) && user.isStudent() == isStudent) {
                this.user = user;
                break;
            }

            System.out.println();
            System.out.println("用户名或密码有误，请重新输入");
            in.nextLine();
            in.nextLine();
        }

        clearScreen();
    }

    public void mainMenu() {
        while (true) {
            clearScreen();
            System.out.println("=====================");
            System.out.println("==      主菜单     ==");
            System.out.println("== 1. 查询学期课程 ==");
            System.out.println("== 2. 修改课程信息 ==");
            System.out.println("== 3. 退出登录     ==");
            System.out.println("=====================");

            int select = inputInfo(1, 3);
            switch (select) {
                case 1:
                    searchCourse();
                    break;
                case 2:
                    modifyCourse();
                    break;
                case 3:
                    return;
            }
        }
    }

    public void searchCourse() {
        clearScreen();
        System.out.println("========================");
        System.out.println("==      课程查询      ==");
        System.out.println("== 1. 查询所有课程    ==");
        System.out.println("== 2. 查询某天课程    ==");
        System.out.println("== 3. 查询某门课课程  ==");
        System.out.println("== 4. 查询某老师课程  ==");
        System.out.println("== 5. 返回            ==");
        System.out.println("========================");

        int select = inputInfo(1, 5);
        ArrayList<Course> res = null;
        switch (select) {
            case 1:
                res = courseTable.searchAll();
                break;
            case 2:
                int day = inputInfo(1, 7, "请输入 1-7 选择星期：");
                res = courseTable.searchByDay(day);
                break;
            case 3:
                String courseName = inputInfo("请输入课程名：", false);
                res = courseTable.searchByCourseName(courseName);
                break;
            case 4:
                String teacherName = inputInfo("请输入老师名", false);
                res = courseTable.searchByTeacherName(teacherName);
                break;
            case 5:
                return;
        }

        System.out.println();
        System.out.println("查询到的课程有：");
        printTable(res);

        in.nextLine();
        in.nextLine();
    }

    public void modifyCourse() {
        clearScreen();

        if (user.isStudent()) {
            System.out.println("学生不允许修改课程信息！");
            in.nextLine();
            in.nextLine();
            return;
        }

        System.out.println("========================");
        System.out.println("==      课程修改      ==");
        System.out.println("========================");

        int day = inputInfo(0, 7, "请输入 0-7 选择星期数(0表示所有星期)：");
        int time = inputInfo(0, 4, "请输入 0-4 选择节数(0表示所有节)：");
        String courseName = inputInfo("请输入课程名(null代表所有课程)：", true);
        String TeacherName = inputInfo("请输入老师名(null代表所有老师)：", true);

        ArrayList<Course> res = courseTable.searchByMulInfo(day, time, courseName, TeacherName);

        int way = inputInfo(1, 2, "请输入数字选择修改方式 1(增加)/2(删除):");
        if (way == 1) {
            if (res.isEmpty()) {
                Course course = new Course(day, time, courseName, TeacherName);
                courseTable.add(course);
                System.out.println(course + " 添加成功");
            } else {
                System.out.println("添加失败，已存在该课程！");
            }
        } else {

            System.out.println();
            System.out.println("删除成功，删除以下");
            System.out.println();
            printTable(res);
            System.out.println();
            System.out.println("共 " + res.size() + " 节课程");
        }

        in.nextLine();
        in.nextLine();
    }

    private int inputInfo(int l, int r) {
        boolean isNotFirst = false;

        int select = 0;
        while (true) {
            try {
                if (isNotFirst)
                    System.out.println();
                else
                    isNotFirst = true;
                System.out.println("请输入数字选择选项：");

                select = in.nextInt();
                if (select < l || select > r)
                    throw new InputInfoExecption();

                break;
            } catch (InputInfoExecption e) {
                e.printMsg();
            }
        }

        return select;
    }

    private int inputInfo(int l, int r, String outputInfo) {
        boolean isNotFirst = false;

        int select = 0;
        while (true) {
            try {
                if (isNotFirst)
                    System.out.println();
                else
                    isNotFirst = true;
                System.out.println(outputInfo);

                select = in.nextInt();
                if (select < l || select > r)
                    throw new InputInfoExecption();

                break;
            } catch (InputInfoExecption e) {
                e.printMsg();
            }
        }

        return select;
    }

    private String inputInfo(String outputInfo, boolean canNull) {
        System.out.println(outputInfo);

        String res = in.next();
        if (canNull && res.equals("null"))
            res = null;

        return res;
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private <T> void printTable(ArrayList<T> table) {
        for (T obj : table)
            System.out.println(obj);
    }
}

// public void searchAbsence() {
// clearScreen();
// System.out.println("== 缺勤查询 ==");

// int week = 0, day = 0, time = 0;
// String courseName = null, studentName = null;

// week = inputInfo(0, 19, "请输入 0-19 选择周数(0表示所有周)：");
// day = inputInfo(0, 7, "请输入 0-7 选择星期数(0表示所有星期)：");
// time = inputInfo(0, 4, "请输入 0-4 选择节数(0表示所有节)：");

// System.out.println("请输入课程名(null代表所有课程)：");
// courseName = in.next();
// if (courseName.equals("null"))
// courseName = null;
// System.out.println("请输入学生名(null代表所有学生)：");
// studentName = in.next();
// if (studentName.equals("null"))
// studentName = null;

// System.out.println();
// ArrayList<SignIn> res = signInTable.search(week, day, time, courseName,
// studentName);

// if (res.isEmpty())
// System.out.println("未查询到任何相关缺勤！");
// else {
// System.out.println("查询到的相关缺勤有：");
// printTable(res);
// }

// in.nextLine();
// in.nextLine();
// }

// --------------------------

// public void modifyAbsence() {
// clearScreen();
// System.out.println("== 缺勤修改 ==");

// if (isStudent) {
// System.out.println("学生不允许修改考勤信息！");
// return;
// }

// int week = 0, day = 0, time = 0, way = 0;
// String courseName = null, studentName = null;

// week = inputInfo(1, 19);
// day = inputInfo(1, 7);
// day = inputInfo(1, 4);

// System.out.println("请输入课程名：");
// courseName = in.next();
// System.out.println("请输入学生名(null代表所有学生)：");
// studentName = in.next();

// way = inputInfo(1, 2, "请输入数字选择修改方式 1(增加)/2(删除):");

// ArrayList<SignIn> res = signInTable.search(week, day, time, courseName,
// studentName);
// if (res.size() != 1)
// System.out.println("未查询到缺勤或查询到多条缺勤！");
// else if (way == 1) {
// courseTable.addRecord(res.get(0));
// System.out.println("增加成功");
// } else {
// courseTable.removeRecord(res.get(0));
// System.out.println("删除成功");
// }

// in.nextLine();
// in.nextLine();
// }