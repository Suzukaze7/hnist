package experiment.experiment12;

public class Part2 {
    public static int i;

    public static void main(String[] args) {
        new addThread().start();
        new addThread().start();
        new delThread().start();
        new delThread().start();
    }
}

class addThread extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println(getName() + "使变量 cnt 加 1: " + ++Part2.i);
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class delThread extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println(getName() + "使变量 cnt 减 1: " + --Part2.i);
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}