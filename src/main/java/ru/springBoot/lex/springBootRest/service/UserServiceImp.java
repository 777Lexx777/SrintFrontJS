package ru.springBoot.lex.springBootRest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ru.springBoot.lex.springBootRest.dao.UserDao;
import ru.springBoot.lex.springBootRest.model.User;
import ru.springBoot.lex.springBootRest.repository.UserRepository;

import java.util.List;

@Component
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;
    UserRepository userRepository;

    @Transactional
    public List<User> getUsers() { return userRepository.findAll(); }

    @Override
    public User getUserByName(String name) { return userDao.getUserByName(name); }

    @Override
    public List<User> getCountUser() { return userDao.getCountUser(); }//count String count

    @Override
    public User getUserId(long id) { return userDao.getUserId(id); }

    @Override
    public void saveUser(User user) { userDao.saveUser(user); }

    @Override
    public void updateUser(User user) { userDao.updateUser(user); }

    @Override
    public void deleteUser(long id) { userDao.deleteUser(id);}
}
