package table.search;

import java.util.ArrayList;

public interface Searchable {
    static final String tableDirPath = "D:/java/study/Experiment/finaltest/src/table/storage/";

    void save();

    <T> ArrayList<T> searchAll();
}