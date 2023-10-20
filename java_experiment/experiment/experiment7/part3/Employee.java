package experiment.experiment7.part3;

public class Employee extends Staff {
    private String department;
    private int salary;

    public Employee(String name, int age, boolean sex, String department, int salary) {
        super(name, age, sex);
        this.department = department;
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
