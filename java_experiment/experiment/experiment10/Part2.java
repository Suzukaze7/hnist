package experiment.experiment10;

import java.util.*;

public class Part2 implements Comparable<Part2> {
    public int id;
    public String name;
    public boolean sex;

    public Part2(int id, String name, boolean sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + (sex ? "男" : "女");
    }

    @Override
    public int hashCode() {
        return id + name.hashCode() + (sex ? 1 : 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Part2))
            return false;

        Part2 y = (Part2) obj;
        return id == y.id && name == y.name && sex == y.sex;
    }

    @Override
    public int compareTo(Part2 y) {
        return id - y.id;
    }

    public static void main(String[] args) {
        Part2 students[] = new Part2[5];

        students[0] = new Part2(333, "Tom", true);
        students[1] = new Part2(222, "Jerry", true);
        students[2] = new Part2(555, "matsuri", true);
        students[3] = new Part2(444, "fubuki", false);
        students[4] = new Part2(111, "aqua", false);

        // ArrayList<Part2> arrayList = new ArrayList<>();
        // for (int i = 0; i < 5; i++)
        // arrayList.add(students[i]);

        // arrayList.sort((x, y) -> {
        // return x.id - y.id;
        // });

        // for (int i = 0; i < 5; i++)
        // System.out.println(arrayList.get(i));
        // System.out.println("-------------");
        // for (Iterator<Part2> it = arrayList.iterator(); it.hasNext();)
        // System.out.println(it.next());

        // System.out.print("\n\n");

        // HashSet<Part2> hashSet = new HashSet<>();
        // for (int i = 0; i < 5; i++)
        // hashSet.add(students[i]);
        // for (Part2 s : hashSet)
        // System.out.println(s);
        // System.out.println("-----------------");
        // for (Iterator<Part2> it = hashSet.iterator(); it.hasNext();)
        // System.out.println(it.next());

        // System.out.print("\n\n");

        // TreeSet<Part2> treeSet = new TreeSet<>();
        // for (int i = 0; i < 5; i++)
        // treeSet.add(students[i]);
        // for (Part2 s : treeSet)
        // System.out.println(s);
        // System.out.println("-----------------");
        // for (Iterator<Part2> it = treeSet.iterator(); it.hasNext();)
        // System.out.println(it.next());
    }
}