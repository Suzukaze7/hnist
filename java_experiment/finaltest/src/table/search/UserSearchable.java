package table.search;

import object.User;

public interface UserSearchable extends Searchable {
    User searchByUserId(String userId);
}