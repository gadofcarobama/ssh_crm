package dao;

import entity.User;

import java.util.List;

public interface UserDao {
    User login(User user);

    List<User> findAll();
}
