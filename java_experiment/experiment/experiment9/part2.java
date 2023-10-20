package experiment.experiment9;

import java.util.Random;

public class part2 {
    public static void main(String[] args) {
        StringBuffer randomString = new StringBuffer();
        Random random = new Random();

        for (int i = 1; i <= 7; i++)
            randomString.append((char) (random.nextInt(26) + 'a'));
        System.out.println(randomString);
    }
}
