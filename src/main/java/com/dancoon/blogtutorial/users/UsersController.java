package com.dancoon.blogtutorial.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;


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
public String registerUser(@ModelAttribute @Validated User userDTO, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
        // If there are validation errors, return to the registration page
        return "users/signup";
    }

    try {
        // Process the user data
        User user = new User();
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail().toLowerCase());
        user.setUsername(userDTO.getUsername());
        
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
