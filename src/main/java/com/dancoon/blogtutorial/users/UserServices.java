package com.dancoon.blogtutorial.users;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServices implements UserServiceInterface {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public UserServices(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(User userDTO) {
        User user = new User();
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail().toLowerCase());
        user.setUsername(userDTO.getUsername());
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserById(Long id){
        return userRepository.findUserById(id);
    }

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
   

} 