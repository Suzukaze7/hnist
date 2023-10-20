package experiment.experiment7.part3;

public class Manager extends Staff {
    private String duty;
    private int annualSalary;

    public Manager(String name, int age, boolean sex, String duty, int annualSalary) {
        super(name, age, sex);
        this.duty = duty;
        this.annualSalary = annualSalary;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public int getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(int annualSalary) {
        this.annualSalary = annualSalary;
    }
}
