package com.HCLJP.taskmanager.controller;

import com.HCLJP.taskmanager.dto.UserDto;
import com.HCLJP.taskmanager.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Log
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public ModelAndView showLogin() {
        return new ModelAndView("login");
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        return new ModelAndView("createform");
    }

    @GetMapping("/registration")
    public ModelAndView showRegistrationForm() {
        return new ModelAndView("registration");
    }

    @PostMapping("/register")
    public ModelAndView registerAccount(UserDto userDto) {

        if (userService.usernameExists(userDto.getUsername())) {
            ModelMap modelMap = new ModelMap();
            modelMap.addAttribute("message", "Username already taken.");
            return new ModelAndView("registration", modelMap);
        }

        userService.registerAccount(userDto);

        return new ModelAndView("login");
    }

}
