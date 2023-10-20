package experiment.Experiment6.part1;

public class Dog {
    private String name;
    private String variety;
    private int age;
    private boolean mood = true;

    public Dog(String name, String variety, int age, boolean mood) {
        this.name = name;
        this.variety = variety;
        this.age = age;
        this.mood = mood;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMood() {
        return mood;
    }

    public String getMood() {
        return mood ? "心情很好" : "心情不好";
    }

    public void setMood(boolean mood) {
        this.mood = mood;
    }

    @Override
    public String toString() {
        return "名字叫" + name + "的" + variety + getMood();
    }

    public void run() {
        System.out.printf("%s，%s\n", toString(), mood ? "开心地围着主人身边转" : "伤心地一动不动");
    }

    public void bark() {
        System.out.printf("%s，%s\n", toString(), mood ? "开心地汪汪叫" : "伤心地呜呜叫");
    }
}
