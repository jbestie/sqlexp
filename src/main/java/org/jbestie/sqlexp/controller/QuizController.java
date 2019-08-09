package org.jbestie.sqlexp.controller;

import org.jbestie.sqlexp.model.QueryResult;
import org.jbestie.sqlexp.model.RequestResponse;
import org.jbestie.sqlexp.model.Task;
import org.jbestie.sqlexp.service.QuizService;
import org.jbestie.sqlexp.service.TaskService;
import org.jbestie.sqlexp.validator.UserFormValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class QuizController {
    
//    final Logger logger = LogManager.getLogger(getClass().getSimpleName());

    final QuizService service;
    final TaskService taskService;
    final UserFormValidator userFormValidator;

    public QuizController(QuizService service, TaskService taskService, UserFormValidator userFormValidator) {
        this.service = service;
        this.taskService = taskService;
        this.userFormValidator = userFormValidator;
    }

//    @InitBinder
//    protected void initBinder(WebDataBinder binder) {
//        binder.setValidator(userFormValidator);
//    }

    @RequestMapping(path = "/submit", method = RequestMethod.GET)
    public ResponseEntity<RequestResponse> submitAnswer(@RequestParam Long id, @RequestParam String query/*, Model model*/) {
        
        String message;
        boolean correct = false;
        QueryResult userQuery = null;
        try {
            userQuery = service.performUsersQuery(query) ;
            QueryResult correctQuery = service.performCorrectQuery(id);
            correct = correctQuery.equals(userQuery);
            message  = correct ? "Correct!" : "Wrong answer";
        } catch (Exception ex) {
            message = ex.getLocalizedMessage();
        }
        
        return new ResponseEntity<>(new RequestResponse(correct, message, userQuery), HttpStatus.OK);
    }
    
    @RequestMapping(path = "/question", method = RequestMethod.GET)
    public String showQuestion (@RequestParam Long questionId, Model model) {
        Task task = taskService.getTask(questionId);
        model.addAttribute("task", task);
        model.addAttribute("tasks", taskService.getAllTasksInCategory(task.getCategory()));
        return "question";
    }

    @RequestMapping(path = "/questions", method = RequestMethod.GET)
    public String showQuestionsList (@RequestParam Long categoryId, Model model) {
        List<Task> tasks = taskService.getAllTasksInCategory(categoryId);
        model.addAttribute("tasks", tasks);
        return "questions";
    }
}
