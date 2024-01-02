package com.dancoon.blogtutorial.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Controller
public class UsersController {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String register() {
         return "users/signup";
    }

@PostMapping("/register")
public String registerUser(@ModelAttribute User user) {
    try {
        // Process the user data
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        user.setUsername(user.getUsername());
        
        // Save user to the database
        userRepository.save(user);

        // Redirect to a success page
        return "redirect:/";
    } catch (Exception e) {
        // Log the exception
        e.printStackTrace();
        // Redirect to an error page
        return "errorpage";
    }
}

}
