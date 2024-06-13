package ru.kata.spring.boot_security.demo.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RolesRepository;
import ru.kata.spring.boot_security.demo.repositories.UsersRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private final UsersRepository usersRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final RolesRepository rolesRepository;


    @Autowired
    public UserServiceImpl(RolesRepository rolesRepository, UsersRepository usersRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.rolesRepository = rolesRepository;
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public User loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = usersRepository.findByUsername(username);

        if (user.isPresent()) {
            Hibernate.initialize(user.get().getRoles());
            return user.get();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public User findUserById(Long userId) {

        Optional<User> userFromDb = usersRepository.findById(userId);

        return userFromDb.orElse(new User());
    }

    public List<User> allUsers() {
        return usersRepository.findAll();
    }


    @Transactional
    public boolean saveUser(User user) {

        Optional<User> userFromDB = usersRepository.findByUsername(user.getUsername());

        if (userFromDB.isEmpty()) {

            //user.setRoles(Collections.singleton(new Role(user.getRoles(), "ROLE_ADMIN")));
            //user.setRoles(user.getRoles());
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            usersRepository.save(user);
            return true;
        } else {
            return false;
        }
    }


    @Transactional
    public boolean deleteUser(Long userId) {

        if (usersRepository.findById(userId).isPresent()) {
            usersRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    @Transactional
    public void update(long id, User updatedUser) {

        User user = findUserById(id);

        if (!updatedUser.getPassword().equals(user.getPassword())) {
            updatedUser.setPassword(bCryptPasswordEncoder.encode(updatedUser.getPassword()));
        }
        //updatedUser.setRoles(updatedUser.getRoles());
        updatedUser.setId(id);
        usersRepository.save(updatedUser);
    }
}
