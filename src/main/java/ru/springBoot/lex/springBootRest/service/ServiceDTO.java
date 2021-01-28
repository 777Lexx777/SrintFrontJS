package ru.springBoot.lex.springBootRest.service;


import org.springframework.stereotype.Component;
import ru.springBoot.lex.springBootRest.model.User;
import ru.springBoot.lex.springBootRest.model.UserDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceDTO {

    public List<UserDTO> getAllUserDTOAsUser(List<User> users) {
        List<UserDTO> userDTO = new ArrayList<>();
        users.forEach(user -> userDTO.add(constructUserDTO(user)));
        return userDTO;
    }

    public User constructUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setAge(userDTO.getAge());
        user.setName(userDTO.getName());
        user.setRoles(userDTO.getRoles());
        user.setPassword(userDTO.getPassword());
        user.setStatus(userDTO.getStatus());
        return user;
    }

    public UserDTO constructUserDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setAge(user.getAge());
        userDTO.setName(user.getName());
        userDTO.setRoles(user.getRoles());
        userDTO.setPassword(user.getPassword());
        userDTO.setStatus(user.getStatus());
        return userDTO;
    }

}
