package finaltest.remove;

import java.util.ArrayList;

import table.search.Searchable;

public interface SignInSearchable extends Searchable {
    ArrayList<SignIn> search(int week, int day, int time, String courseName, String studentName);
}
