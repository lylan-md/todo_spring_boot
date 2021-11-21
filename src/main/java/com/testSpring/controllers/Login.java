package com.testSpring.controllers;

import com.testSpring.entities.User;
import com.testSpring.repositories.TasksRepository;
import com.testSpring.repositories.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/login")
public class Login {
    @Autowired
    private UserRepository userRepository;
    private final String emailRegexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    @GetMapping
    public String index() {
        return "login";
    }

    @PostMapping("/auth")
    public String auth (@RequestParam String email, @RequestParam String password, @RequestParam String action, Model model) {
        switch (action) {
            case "SignIn":
                try {
                    String hashOfPassword = DigestUtils.sha1Hex(password);
                    this.signIn(email, hashOfPassword);
                    model.addAttribute("email", email);
                } catch (Exception e) {
                    model.addAttribute("login_error", e.getMessage());
                }
                break;
            case "Create":
                break;
            default:
                break;
        }

        return "login";
    }

    private void signIn(String email, String password) throws Exception {
        if (!Pattern.compile(this.emailRegexPattern).matcher(email).matches()) {
            throw new Exception("Invalid email!");
        }

        if (password.length() == 0) {
            throw new Exception("Empty password!");
        }

        Optional<User> optionalUser = userRepository.findByEmailAndPassword(email, password);

        if (optionalUser.isEmpty()) {
            throw new Exception("Password or Email incorrect!");
        }
    }
}
