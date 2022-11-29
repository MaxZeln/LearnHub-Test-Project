package ru.learnhub.learnhubtestproject.controller;


import ch.qos.logback.core.model.Model;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.learnhub.learnhubtestproject.entity.User;
import ru.learnhub.learnhubtestproject.mapper.RoleMapper;
import ru.learnhub.learnhubtestproject.mapper.UserMapper;
import ru.learnhub.learnhubtestproject.model.RoleDto;
import ru.learnhub.learnhubtestproject.model.UserDto;
import ru.learnhub.learnhubtestproject.service.RoleService;
import ru.learnhub.learnhubtestproject.service.UserService;
import ru.learnhub.learnhubtestproject.util.UserValidator;
import ru.learnhub.learnhubtestproject.view.RoleView;
import ru.learnhub.learnhubtestproject.view.UserView;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final UserValidator userValidator;
    private final RoleService roleService;
    private final UserService userService;

    public AuthController(UserMapper userMapper,
                          RoleMapper roleMapper,
                          UserValidator userValidator,
                          RoleService roleService,
                          UserService userService) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.userValidator = userValidator;
        this.roleService = roleService;
        this.userService = userService;
    }


    @GetMapping("/login")
    public ModelAndView index () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("html/login");
        return modelAndView;
    }

    @GetMapping("/hello")
    public ModelAndView sayHello() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hello");
        return modelAndView;
    }

    @GetMapping("/registration")
    public ModelAndView registrationPage(@ModelAttribute("view") UserView view) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.html");
        return modelAndView;
    }

    @PostMapping("/registration")
    @ResponseBody
    public ModelAndView performRegistration(@ModelAttribute("view") @Valid UserView view
            , BindingResult bindingResult) throws IOException {

        RoleDto roleDto = roleService.findById(2);
        view.setRole(roleMapper.mapToView(roleDto));

        UserDto userDto = userMapper.mapFromView(view);

        userValidator.validate(userDto, bindingResult);

        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/index");
            return modelAndView;
        }

        userService.create(userDto);


        ModelAndView login = new ModelAndView();
        login.setViewName("html/login");
        return login;
    }
}
