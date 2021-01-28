package ru.springBoot.lex.springBootRest.service;

import ru.springBoot.lex.springBootRest.model.User;

import java.util.List;

public interface UserService {
    User getUserByName(String name);

    List<User> getCountUser();//String count

    User getUserId(long id);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(long id);

}
