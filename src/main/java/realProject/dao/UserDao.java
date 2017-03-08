package realProject.dao;

import realProject.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);

    void updateUser(User user);

    void removeUser(int id);

    User getUserById(int id);

    List<User> usersList();

    List<User> findUsersByPage(int pageid,int total);
}
