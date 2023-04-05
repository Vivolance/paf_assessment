package ibf2022.paf.assessment.server.controllers;

import ibf2022.paf.assessment.server.DeserUtils;
import ibf2022.paf.assessment.server.models.TaskUpdateException;
import ibf2022.paf.assessment.server.models.User;
import ibf2022.paf.assessment.server.models.UserWithTask;
import ibf2022.paf.assessment.server.services.TodoService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;

// TODO: Task 4, Task 8

@Controller
@RequestMapping(path="/")
public class TasksController {

    @Autowired
    TodoService todoService;

    @PostMapping(path = "/task", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView postTransfer(@RequestBody String payload, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.printf(">>> payload: %s\n", payload);
        // if date parsing error, return a status code 400 bad request
        try {
            UserWithTask userWithTask = DeserUtils.toUserWithTask(payload);
            User dummyUser = new User();
            dummyUser.setName(userWithTask.getUserName());
            todoService.upsertTask(userWithTask.getTasks(), dummyUser);
            // display result.html
            modelAndView.setViewName("result");
            // set status code to 200 - Success
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (ParseException | TaskUpdateException e) {
            // display error.html
            modelAndView.setViewName("error");
            // set status code to 400 - bad request
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return modelAndView;
    }
}