
package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

@Controller
public class ViewController {

    private final UserService userService;

    @Autowired
    public ViewController(RestTemplate restTemplate, UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String showIndexPageAdmin(Model model) {

        model.addAttribute("allUsers", userService.allUsers());
        model.addAttribute("userForm", new User());

        return "admin";
    }

}
