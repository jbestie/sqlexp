package org.jbestie.sqlexp.controller;

import org.apache.commons.logging.impl.Log4JLogger;
import org.jbestie.sqlexp.model.QueryResult;
import org.jbestie.sqlexp.model.RequestResponse;
import org.jbestie.sqlexp.service.SqlExpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RequestController {
    
    Log4JLogger logger = new Log4JLogger(getClass().getSimpleName());
    
    @Autowired
    SqlExpService service;
    
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String indexPage() {
        return "index";
    }
    
    @RequestMapping(path = "/submit", method = RequestMethod.GET)
    public ResponseEntity<RequestResponse> sumbitAnswer (@RequestParam Long questionId, @RequestParam String query, Model model) {
        
        String message = "";
        boolean correct = false;
        QueryResult userQuery = null;
        try {
            userQuery = service.performUsersQuery(questionId, query) ;
            QueryResult correctQuery = service.performCorrectQuery(questionId);
            correct = correctQuery.equals(userQuery);
            message  = correct ? "Correct!" : "Wrong answer";
        } catch (Exception ex) {
            message = ex.getLocalizedMessage();
        }
        model.addAttribute("message", message);
        model.addAttribute("questionId", questionId);
        
        return new ResponseEntity<RequestResponse>(new RequestResponse(correct, message, userQuery), HttpStatus.OK);
    }
    
    
    
    @RequestMapping(path = "/question", method = RequestMethod.GET)
    public String showQuestion (@RequestParam Long questionId, Model model) {
        String description = service.getTaskDescription(questionId);
        model.addAttribute("description", description);
        return "question";
    }
}
