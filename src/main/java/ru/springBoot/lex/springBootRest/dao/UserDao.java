package ru.springBoot.lex.springBootRest.dao;

import ru.springBoot.lex.springBootRest.model.User;

import java.util.List;


public interface UserDao {

    User getUserByName(String name);

    List<User> getCountUser();//String count

    User getUserId(long id);

    void saveUser(User user);

    void updateUser(User user);//long id,

    void deleteUser(long id);


}
