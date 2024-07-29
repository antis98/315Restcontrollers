package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.validation.Valid;

@RestController
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView loginPage() {

        return new ModelAndView("/login");
    }

    @PostMapping("/registration")
    public ModelAndView addUser(@ModelAttribute("userForm") @RequestBody @Valid User userForm,
                          BindingResult bindingResult, Model model) {

        ModelAndView mav = new ModelAndView("redirect:/admin");

        if (bindingResult.hasErrors()) {
            return null;
        }
        if (!userService.saveUser(userForm)) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");

            return null;
        }
        return mav;
    }

}
