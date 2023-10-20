package object;

import java.io.Serializable;

public class User implements Serializable {
    private String userId;
    private String password;

    private String realName;
    private int age;
    private boolean sex;
    private boolean isStudent;

    public User(String userId, String password, String realName, int age, boolean sex, boolean isStudent) {
        this.userId = userId;
        this.password = password;
        this.realName = realName;
        this.age = age;
        this.sex = sex;
        this.isStudent = isStudent;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getRealName() {
        return realName;
    }

    public int getAge() {
        return age;
    }

    public boolean isSex() {
        return sex;
    }

    public boolean isStudent() {
        return isStudent;
    }
}