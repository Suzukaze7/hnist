package experiment.experiment12;

public class Part1 extends Thread {

    @Override
    public void run() {
        for (int i = 1; i <= 3; i++)
            System.out.println(getName() + ": " + i);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 3; i++) {
            System.out.println("子线程第" + i + "次循环");
            new Part1().start();
            sleep(1000);

            System.out.println("主线程第" + i + "次循环");
            for (int j = 1; j <= 3; j++)
                System.out.println(Thread.currentThread().getName() + ": " + j);
        }
    }
}
