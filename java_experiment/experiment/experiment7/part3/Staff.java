package experiment.experiment7.part3;

public class Staff {
    private String name;
    private int age;
    private boolean sex;

    public Staff(String name, int age, boolean sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex ? "男" : "女";
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }
}
