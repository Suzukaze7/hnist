package experiment.Experiment6.part2;

public class StudentList {
    static private int studentCount;
    static private Student students[] = new Student[100];

    public static int getStudentCount() {
        return studentCount;
    }

    public static Student[] getStudents() {
        return students;
    }

    public void addStudent(long studentNumber, int age, int score) {
        students[studentCount++] = new Student(studentNumber, age, score);
    }

    public double average() {
        int sum = 0;
        for (int i = 0; i < studentCount; i++)
            sum += students[i].getScore();
        return (double) sum / studentCount;
    }

    private class Student {
        private long studentNumber;
        private int age;
        private int score;

        public Student(long studentNumber, int age, int score) {
            this.studentNumber = studentNumber;
            this.age = age;
            this.score = score;
        }

        public long getStudentNumber() {
            return studentNumber;
        }

        public void setStudentNumber(long studentNumber) {
            this.studentNumber = studentNumber;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }
}
