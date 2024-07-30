package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.web.bind.annotation.RestController;


public class UsersController {

}

/*

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
public class UsersController {

    private final RestTemplate restTemplate; // = new RestTemplate();

    private final UserService userService;

    @Autowired
    public UsersController(RestTemplate restTemplate, UserService userService) {
        this.restTemplate = restTemplate;
        this.userService = userService;
    }



    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @GetMapping ("/users")
    public ResponseEntity<List<User>> users() {

        return new ResponseEntity<>(userService.allUsers(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @PostMapping("/users")
    public ResponseEntity<HttpStatus> saveNewUser(@RequestBody @Valid User user) {
        userService.saveUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> saveUpdateUser(@RequestBody User user, @PathVariable("id") long id,
                                                     BindingResult bindingResult) {
        userService.update(id, user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/showUser")
    public ResponseEntity<User> showUser(Principal principal, Model model) {

        return new ResponseEntity<>(userService.loadUserByUsername(principal.getName()), HttpStatus.OK);
    }
}
*/