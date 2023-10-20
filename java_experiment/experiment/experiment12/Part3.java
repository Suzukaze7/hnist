package experiment.experiment12;

public class Part3 extends Thread {

    public Part3(String name) {
        super(name);
    }

    private static String names[] = { "t1", "t2", "t3" };
    private static int idx;
    private static final Object obj = new Object();

    @Override
    public void run() {
        synchronized (obj) {
            while (getName() != names[idx])
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            for (int i = 1; i <= 10; i++)
                System.out.println(getName() + "循环第 " + i + " 次");
            System.out.println();
            obj.notifyAll();
            idx++;
        }
    }

    public static void main(String[] args) {
        new Part3("t1").start();
        new Part3("t2").start();
        new Part3("t3").start();
    }
}