package com.testSpring.controllers;

import com.testSpring.entities.User;
import com.testSpring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/registration")
public class Registration {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    private final String userRole = "ROLE_USER";

    @PostMapping
    public String register(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttrs, HttpServletRequest http) {
        System.out.println(username + " " + password);

        try {
            this.validateRegistrationData(username, password);
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("registerException", e.getMessage());
            return "redirect:login";
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setActive(true);
        user.setRoles(this.userRole);
        this.userRepository.save(user);

        try {
            http.login(username, password);
        } catch (ServletException e) {
            redirectAttrs.addFlashAttribute("registerException", e.getMessage());
        }

        return "redirect:myday";
    }

    private void validateRegistrationData(String username, String password) throws Exception {
        if (username.length() == 0) {
            throw new Exception("Empty username");
        }

        if (password.length() == 0) {
            throw new Exception("Empty password");
        }

        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            throw new Exception("User already exists");
        }
    }
}
