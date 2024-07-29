package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UsersController {

    private final RestTemplate restTemplate; // = new RestTemplate();

    private final UserService userService;

    @Autowired
    public UsersController(RestTemplate restTemplate, UserService userService) {
        this.restTemplate = restTemplate;
        this.userService = userService;
    }


/*
    @GetMapping("/admin")
    public List<User> userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        model.addAttribute("userForm", new User());

        return userService.allUsers();
        }
*/


    /*
    @GetMapping("/admin")
    public String users(Model model) {


        model.addAttribute("allUsers", userService.allUsers());
        model.addAttribute("userForm", new User());

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity <String> entity = new HttpEntity<String>(headers);

        return restTemplate.exchange("http://localhost:8080/admin", HttpMethod.GET, entity, String.class).getBody();
    }
*/

    /*
    @GetMapping // ("/admin")
    public ModelAndView users(Model model) {

        ModelAndView mav = new ModelAndView("admin3");

        model.addAttribute("allUsers", userService.allUsers());
        model.addAttribute("userForm", new User());

        return mav;
    }
*/
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @GetMapping ("/users")
    public ResponseEntity<List<User>> users() {

        return new ResponseEntity<>(userService.allUsers(), HttpStatus.OK);
    }

    /*
    @DeleteMapping("/{id}")
    public ModelAndView deleteUser(@RequestParam(required = true, defaultValue = "") Long userId,
                             @RequestParam(required = true, defaultValue = "") String action,
                             Model model, @PathVariable("id") long id) {

        ModelAndView mav = new ModelAndView("redirect:/admin");

        userService.deleteUser(id);
        return mav;
    }*/



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


    private String getErrorsFromBindingResult(BindingResult bindingResult) {
        return bindingResult.getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.joining("; "));
    }



    @PatchMapping("/{id}")
    public ModelAndView update(@ModelAttribute("user") @RequestBody @Valid User user, BindingResult bindingResult, // ModelAndView
                         @PathVariable("id") long id) {

        ModelAndView mav = new ModelAndView("redirect:/admin");

        if (bindingResult.hasErrors())
            return null; // mav;

        userService.update(id, user);
        return mav; // mav;
    }


/*

    @GetMapping("/showUser")
    public ModelAndView showUser(Principal principal, Model model) {

        ModelAndView mav = new ModelAndView("/user");

        model.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        //return userService.loadUserByUsername(principal.getName());
        return mav;
    }
*/

    @GetMapping("/showUser")
    public ResponseEntity<User> showUser(Principal principal, Model model) {

        //ModelAndView mav = new ModelAndView("/user");

        //model.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        //return userService.loadUserByUsername(principal.getName());
        return new ResponseEntity<>(userService.loadUserByUsername(principal.getName()), HttpStatus.OK);

    }
}
