package realProject.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import realProject.dao.UserDao;
import realProject.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;


    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        this.userDao.addUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        this.userDao.updateUser(user);
    }

    @Override
    @Transactional
    public void removeUser(int id) {
        this.userDao.removeUser(id);
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        return this.userDao.getUserById(id);
    }

    @Override
    @Transactional
    public List<User> usersList() {
        return this.userDao.usersList();
    }

    @Transactional
    public List<User> findUsersByPage(int pageid,int total) {
        return userDao.findUsersByPage(pageid, total);
    }

}
