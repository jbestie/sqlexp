package org.jbestie.sqlexp.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jbestie.sqlexp.enums.Role;
import org.jbestie.sqlexp.model.TaskCategory;
import org.jbestie.sqlexp.model.User;
import org.jbestie.sqlexp.service.TaskService;
import org.jbestie.sqlexp.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class UserController {
    private final TaskService taskService;
    private final UserService userService;

    private final Logger logger = LogManager.getLogger(getClass().getSimpleName());
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @RequestMapping(path = "/*", method = RequestMethod.GET)
    public String indexPage(Model model, Principal principal) {
        if (principal != null) {
            List<TaskCategory> categories = taskService.getAllCategories();
            model.addAttribute("categories", categories);
            logger.warn(principal);
        }
        return "index";
    }

    @RequestMapping(path = "/registration", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }


    @RequestMapping(path = "/createuser", method = RequestMethod.POST)
    public String createUser(@ModelAttribute("userForm") @Validated User user, BindingResult result, Model model,
                             final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "registration";
        }

        logger.info(user);

        // fill automatically system info
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(Role.ROLE_USER);
        user.setRegistrationDate(LocalDateTime.now());
        userService.createUser(user);

        return "redirect:/";
    }
}
