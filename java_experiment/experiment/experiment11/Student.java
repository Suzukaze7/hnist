package experiment.experiment11;

import java.io.Serializable;

public class Student implements Serializable {
    String name;
    String id;
    int age;
    String profession;
    boolean isParty;

    @Override
    public String toString() {
        return name + " " + id + " " + age + " " + profession + " " + (isParty ? "is party" : "isn't party");
    }

    public Student(String name, String id, int age, String profession, boolean isParty) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.profession = profession;
        this.isParty = isParty;
    }
}
