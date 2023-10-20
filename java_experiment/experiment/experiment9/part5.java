package experiment.experiment9;

import java.util.Scanner;
import java.util.regex.*;

public class part5 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String regex = "^\\s*\\w+(?:\\.?[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
        String content = in.next();

        if (Pattern.matches(regex, content))
            System.out.println(content + "是一个邮箱");
        else
            System.out.println(content + "不是个邮箱");

        regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find())
            System.out.println(content.substring(matcher.start(), matcher.end()));
    }
}
