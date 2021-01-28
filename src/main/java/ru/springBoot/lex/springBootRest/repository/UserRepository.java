 package ru.springBoot.lex.springBootRest.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.springBoot.lex.springBootRest.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
        Optional<User> findByName(String name);

}
//Ripository