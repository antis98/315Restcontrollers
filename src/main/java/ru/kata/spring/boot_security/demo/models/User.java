package ru.kata.spring.boot_security.demo.models;

import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;
import java.util.StringJoiner;

@Entity
@Table(name = "person")
public class User implements UserDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    @NotEmpty(message = "Обязательное поле")
    @Email(message = "Некоректный email")
    private String username;

    @Column(name = "first_name")
    @NotEmpty(message = "Обязательное поле")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "Обязательное поле")
    private String lastName;

    @Column(name = "age")
    @Min(value = 1, message = "Возраст должен быть больше 0")
    private String age;

    @Column(name = "password")
    private String password;

    public User() {
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @NotEmpty(message = "Не выбрана роль")
    private Set<Role> roles;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {

        StringJoiner joiner = new StringJoiner(", ");
        for (Role role : roles) {
            joiner.add(role.getName());
        }

        return "пользователь: " + "id: " + id +
                ", username: " + username +
                ", roles: " + joiner;
    }
}
