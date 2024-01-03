package com.dancoon.blogtutorial.users;

import java.util.List;

public interface UserServiceInterface {
    void saveUser(User userDTO);
    User findByUsername(String username);
    User findByEmail(String email);
    User findUserById(Long id);
    List<User> getAllUsers();
}
