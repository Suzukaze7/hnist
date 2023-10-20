package table;

import java.io.*;
import java.util.ArrayList;

import object.User;
import table.search.UserSearchable;

public class UserTable extends Thread implements UserSearchable {
    private final String tablePath = tableDirPath + "user.txt";
    private final ArrayList<User> table = new ArrayList<>();

    public UserTable() {
        start();
    }

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tablePath));

            while (true)
                try {
                    table.add((User) ois.readObject());
                } catch (EOFException e) {
                    break;
                }

            ois.close();
        } catch (Exception e) {
        }
    }

    @Override
    public void save() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tablePath));
            for (User user : table)
                oos.writeObject(user);
            oos.close();
        } catch (IOException e) {
        }
    }

    @Override
    public ArrayList<User> searchAll() {
        return new ArrayList<User>(table);
    }

    @Override
    public User searchByUserId(String userId) {
        User res = null;
        for (User u : table)
            if (u.getUserId().equals(userId)) {
                res = u;
                break;
            }

        return res;
    }
}