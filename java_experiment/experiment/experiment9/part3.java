package experiment.experiment9;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Scanner;

public class part3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        final String intToWeek[] = { "一", "二", "三", "四", "五", "六", "日" };

        String dateStr = in.next();
        LocalDate date = LocalDate.parse(dateStr);
        System.out.println(dateStr + "是星期" + intToWeek[date.get(ChronoField.DAY_OF_WEEK) - 1]);
    }
}
