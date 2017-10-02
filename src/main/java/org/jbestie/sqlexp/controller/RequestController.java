package org.jbestie.sqlexp.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.impl.Log4JLogger;
import org.jbestie.sqlexp.enums.Role;
import org.jbestie.sqlexp.model.QueryResult;
import org.jbestie.sqlexp.model.RequestResponse;
import org.jbestie.sqlexp.model.Task;
import org.jbestie.sqlexp.model.User;
import org.jbestie.sqlexp.service.SqlExpService;
import org.jbestie.sqlexp.service.TaskService;
import org.jbestie.sqlexp.service.UserService;
import org.jbestie.sqlexp.validator.UserFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RequestController {
    
    Log4JLogger logger = new Log4JLogger(getClass().getSimpleName());
    
    @Autowired
    SqlExpService service;
    
    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;

    @Autowired
    UserFormValidator userFormValidator;
    
    
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
//    @InitBinder
//    protected void initBinder(WebDataBinder binder) {
//        binder.setValidator(userFormValidator);
//    }
    
    
    @RequestMapping(path = "/*", method = RequestMethod.GET)
    public String indexPage() {
        return "index";
    }
    
    @RequestMapping(path = "/submit", method = RequestMethod.GET)
    public ResponseEntity<RequestResponse> sumbitAnswer (@RequestParam Long id, @RequestParam String query, Model model) {
        
        String message = "";
        boolean correct = false;
        QueryResult userQuery = null;
        try {
            userQuery = service.performUsersQuery(id, query) ;
            QueryResult correctQuery = service.performCorrectQuery(id);
            correct = correctQuery.equals(userQuery);
            message  = correct ? "Correct!" : "Wrong answer";
        } catch (Exception ex) {
            message = ex.getLocalizedMessage();
        }
        
        return new ResponseEntity<RequestResponse>(new RequestResponse(correct, message, userQuery), HttpStatus.OK);
    }
    
    
    
    @RequestMapping(path = "/question", method = RequestMethod.GET)
    public String showQuestion (@RequestParam Long questionId, Model model) {
        Task task = taskService.getTask(questionId);
        model.addAttribute("task", task);
        model.addAttribute("questionList", service.getAllQuestionNames());
        return "question";
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
        
        return "redirect:index";
    }


    @RequestMapping(path = "/editor/edit", method = RequestMethod.GET)
    public String editTask(Model model) {
        Map<Long, String> categories = new HashMap<>();
        categories.put(1L, "TestCategory");

        model.addAttribute("categories", categories);
        return "editor/editor";
    }


    @RequestMapping(path = "/editor/createtask", method =  RequestMethod.POST)
    public String createTask(@ModelAttribute("task") Task task) {
        logger.info("Task is " + task);
        return "index";
    }
}
