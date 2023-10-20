package experiment.experiment10;

import java.util.*;

public class Part1 implements Comparable<Part1> {
    public int id;
    public String name;
    public boolean sex;

    public Part1(int id, String name, boolean sex) {
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
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }

    @Override
    public int compareTo(Part1 o) {
        // TODO Auto-generated method stub
        return 0;
    }

    public static void main(String[] args) {
        Part1 students[] = new Part1[5];

        students[0] = new Part1(333, "Tom", true);
        students[1] = new Part1(222, "Jerry", true);
        students[2] = new Part1(555, "matsuri", true);
        students[3] = new Part1(444, "fubuki", false);
        students[4] = new Part1(111, "aqua", false);

        ArrayList<Part1> arrayList = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            arrayList.add(students[i]);

        arrayList.sort((x, y) -> {
            return x.id - y.id;
        });

        for (int i = 0; i < 5; i++)
            System.out.println(arrayList.get(i));
        System.out.println("-------------");
        for (Iterator<Part1> it = arrayList.iterator(); it.hasNext();)
            System.out.println(it.next());

        System.out.print("\n\n");

        HashSet<Part1> hashSet = new HashSet<>();
        for (int i = 0; i < 5; i++)
            hashSet.add(students[i]);
        for (Part1 s : hashSet)
            System.out.println(s);
        System.out.println("-----------------");
        for (Iterator<Part1> it = hashSet.iterator(); it.hasNext();)
            System.out.println(it.next());

        System.out.print("\n\n");

        TreeSet<Part1> treeSet = new TreeSet<>();
        for (int i = 0; i < 5; i++)
            treeSet.add(students[i]);
        for (Part1 s : treeSet)
            System.out.println(s);
        System.out.println("-----------------");
        for (Iterator<Part1> it = treeSet.iterator(); it.hasNext();)
            System.out.println(it.next());
        Math.max(0, 0);
    }
}
