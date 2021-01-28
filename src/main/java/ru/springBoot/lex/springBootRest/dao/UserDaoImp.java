package ru.springBoot.lex.springBootRest.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.springBoot.lex.springBootRest.model.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Transactional(readOnly = true)
@Component
public class UserDaoImp implements UserDao {

    @PersistenceContext//(unitName = "em")
    private EntityManager entityManager;

    @Override
    public User getUserByName(String name) {
        TypedQuery<User> q =
                entityManager.createQuery("select u from User u where u.name = : name", User.class);
        q.setParameter("name", name);
        System.out.println("getUserByName dao" + name);
        return  q.getResultList().stream().filter(user -> user.getName().equals(name))
                .findAny().orElse(null);
    }

    @Override
    public List<User> getCountUser() { //String count
       // int countUsers = Integer.parseInt(count);
        List<User> userList = entityManager.createQuery("select u from User u ", User.class).getResultList();
//        System.out.println(userList + " List output");
//        if (countUsers <=0 || countUsers >= 5){ return userList.subList(0, userList.size());}
//            return userList.subList(0 , countUsers);
        return userList;

    }

    @Override
    public User getUserId(long id) {
        System.out.println("id dao");
        TypedQuery<User> q =
                entityManager.createQuery("select u from User u where u.id = : id", User.class);
        q.setParameter("id", id);
        return  q.getResultList().stream().filter(user -> user.getId() == id)
                .findAny().orElse(null);
    }

    @Override
    @Transactional
    public void saveUser(User user){
        System.out.println("dao save " + user);
        entityManager.persist(user); }

    @Override
    @Transactional
    public void updateUser( User user){ entityManager.merge(user);}

    @Override
    @Transactional
    public void deleteUser(long id){ entityManager.remove(getUserId(id));}
}
