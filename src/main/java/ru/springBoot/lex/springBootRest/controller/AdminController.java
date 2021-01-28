package ru.springBoot.lex.springBootRest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.springBoot.lex.springBootRest.model.User;
import ru.springBoot.lex.springBootRest.model.UserDTO;
import ru.springBoot.lex.springBootRest.service.ServiceDTO;
import ru.springBoot.lex.springBootRest.service.UserServiceImp;
import java.util.List;

@RestController
@RequestMapping()
public class AdminController {

    private final UserServiceImp userServiceImp;
    private final ServiceDTO serviceDTO;

    @Autowired
    public AdminController(UserServiceImp userServiceImp, ServiceDTO serviceDTO) {
        this.userServiceImp = userServiceImp;
        this.serviceDTO = serviceDTO;
    }

    @GetMapping("/userList")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ModelAndView userInfo() {
        return new ModelAndView("/index");
    }

    @GetMapping("/userList/info")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<UserDTO>> index( ) {
        List<UserDTO> dtoUsers = serviceDTO.getAllUserDTOAsUser(userServiceImp.getCountUser());
        return new ResponseEntity<>(dtoUsers, HttpStatus.OK);
    }

    @PostMapping("/updateUser")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<UserDTO>  updateUser( @RequestBody UserDTO userDTO) {
        userServiceImp.updateUser(serviceDTO.constructUser(userDTO));
        return new ResponseEntity<>(userDTO, HttpStatus.OK) ;
    }

    @PostMapping("/newUser")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<UserDTO> createNewUsers(@RequestBody UserDTO userDTO) {
        System.out.println("createNew");

        userServiceImp.saveUser(serviceDTO.constructUser(userDTO));
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteUser/{id}")//
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        userServiceImp.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK) ;
    }

    //TODO изменил
    @GetMapping("/user/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<UserDTO> printUsersId(@PathVariable("id") long id){
        UserDTO userDTO = serviceDTO.constructUserDTO(userServiceImp.getUserId(id));
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

///
//    @PostMapping("/newUser")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public ResponseEntity<User> createNewUsers(@RequestBody User user) {///@ModelAttribute("newUser")
//            System.out.println("createNew");
//        userServiceImp.saveUser(user);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }


}
