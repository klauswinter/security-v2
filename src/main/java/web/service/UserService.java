package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    void updateUser(long id, User user);

    User getUserById(long id);

    void removeUserById(long id);

    List<User> getAllUsers();
}
