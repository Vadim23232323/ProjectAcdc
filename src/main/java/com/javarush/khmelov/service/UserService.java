package com.javarush.khmelov.service;

import com.javarush.khmelov.entity.Role;
import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.dao.UserDAO;
import com.javarush.khmelov.util.BasicPasswordEncoder;
import com.javarush.khmelov.util.PasswordEncoder;

import java.util.Collection;
import java.util.Optional;


public class UserService {

    private final UserDAO userRepository;

    public UserService(UserDAO userRepository) {
        this.userRepository = userRepository;
    }

    public User add(String login, String password, String name, String surname, Long roleId) {

        PasswordEncoder passwordEncoder = new BasicPasswordEncoder();

        Role role = userRepository.findRoleById(roleId);

        return User.builder()
                .login(login)
                .password(passwordEncoder.encode(password))
                .name(name)
                .surname(surname)
                .role(role)
                .build();
    }
    public void create(User user) {
        userRepository.create(user);
    }

    public void update(User user) {

        userRepository.update(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public Collection<User> getAll() {
        return userRepository.getAll();
    }

    public Optional<User> get(long id) {
        return userRepository.get(id);
    }

    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public Role findRoleById(Long id) {
        return userRepository.findRoleById(id);
    }

}
