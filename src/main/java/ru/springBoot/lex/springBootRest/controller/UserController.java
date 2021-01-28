package ru.springBoot.lex.springBootRest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.springBoot.lex.springBootRest.model.User;
import ru.springBoot.lex.springBootRest.service.UserServiceImp;

import java.security.Principal;

//@Controller
@RestController
@RequestMapping()
public class UserController {

    private final UserServiceImp userServiceImp;
    
    @Autowired
    public UserController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }
//
//    @GetMapping("/name")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
//    public String printUserId(Model model, Principal principal) {
//        String username = principal.getName();
//        model.addAttribute("user", username);
//        System.out.println("user id" + username);
//        model.addAttribute("userInfo", userServiceImp.getUserByName(username));
//        return "/userInfo";
////        return userServiceImp.getUserByName(username);
//    }

    @GetMapping("/name")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ModelAndView userInfo(Model model,Principal principal) {
        String username = principal.getName();
        model.addAttribute("user", username);
        return new ModelAndView("/userInfo");
    }


    @GetMapping("/name/info")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<User> printUserId(Principal principal) {
        String username = principal.getName();
        return new ResponseEntity<>(userServiceImp.getUserByName(username), HttpStatus.OK);
    }
//    @GetMapping("/name/info")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
//    public User printUserId(Principal principal) {
//        String username = principal.getName();
//        return  userServiceImp.getUserByName(username);
//    }

}
//    String username = principal.getName();
//        model.addAttribute("user", username);
//                System.out.println("user id" + username);
//                model.addAttribute("userInfo", userServiceImp.getUserByName(username));
//
//                return userServiceImp.getUserByName(username);