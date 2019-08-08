package org.jbestie.sqlexp.controller;

import org.jbestie.sqlexp.model.QueryResult;
import org.jbestie.sqlexp.model.RequestResponse;
import org.jbestie.sqlexp.model.Task;
import org.jbestie.sqlexp.model.TaskCategory;
import org.jbestie.sqlexp.service.QuizService;
import org.jbestie.sqlexp.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EditorController {
    private final TaskService taskService;
    private final QuizService quizService;

    public EditorController(TaskService taskService, QuizService quizService) {
        this.taskService = taskService;
        this.quizService = quizService;
    }

    @RequestMapping(path = "/editor/", method = RequestMethod.GET)
    public String taskList(Model model) {
        List<Task> taskList = taskService.getAllTasks();
        List<TaskCategory> categories = taskService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("taskList", taskList);
        return "editor/index";
    }

    @RequestMapping(path = "/editor/create", method = RequestMethod.GET)
    public String createTask(Model model) {
        List<TaskCategory> categories = taskService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("task", new Task());
        return "editor/create";
    }

    @RequestMapping(path = "/editor/edit", method = RequestMethod.GET)
    public String editTask(@RequestParam Long id, Model model) {
        List<TaskCategory> categories = taskService.getAllCategories();

        model.addAttribute("categories", categories);
        model.addAttribute("task", taskService.getTask(id));
        return "editor/editor";
    }

    @RequestMapping(path = "/editor/updateTask", method =  RequestMethod.POST)
    public String updateTask(@ModelAttribute("task") Task task) {
        taskService.updateTask(task);
        return "redirect:/editor/";
    }

    @RequestMapping(path = "/editor/createTask", method =  RequestMethod.POST)
    public String createTask(@ModelAttribute("task") Task task) {
        taskService.createTask(task);
        return "redirect:/editor/";
    }

    @RequestMapping(path = "/editor/randomQuery", method = RequestMethod.GET)
    public ResponseEntity<RequestResponse> executeRandomQuery(@RequestParam String query) {

        String message;
        QueryResult userQuery = null;
        boolean correct = true;
        try {
            userQuery = quizService.performUsersQuery(query) ;
            message = "Ok";
        } catch (Exception ex) {
            message = ex.getLocalizedMessage();
            correct = false;
        }

        return new ResponseEntity<>(new RequestResponse(correct, message, userQuery), HttpStatus.OK);
    }

    @RequestMapping(path = "/editor/create_category", method = RequestMethod.GET)
    public String createTaskCategory(Model model) {
        model.addAttribute("category", new TaskCategory(-1L, ""));
        return "editor/create_category";
    }

    @RequestMapping(path = "/editor/createCategory", method =  RequestMethod.POST)
    public String createTaskCategory(@ModelAttribute("category") TaskCategory taskCategory) {
        taskService.createCategory(taskCategory);
        return "redirect:/editor/";
    }
}
