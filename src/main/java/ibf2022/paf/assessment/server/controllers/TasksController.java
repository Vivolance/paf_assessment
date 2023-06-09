package ibf2022.paf.assessment.server.controllers;

import ibf2022.paf.assessment.server.DeserUtils;
import ibf2022.paf.assessment.server.models.TaskUpdateException;
import ibf2022.paf.assessment.server.models.UserWithTask;
import ibf2022.paf.assessment.server.services.TodoService;
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
        // For debugging
        // System.out.println(">>> payload: %s\n", payload);
        // If we get a date parsing error, return a status code 400 bad request
        try {
            UserWithTask userWithTask = DeserUtils.toUserWithTask(payload);
            String userName = userWithTask.getUserName();
            todoService.upsertTask(userWithTask.getTasks(), userName);
            // display result.html
            modelAndView.setViewName("result");
            // add "taskCount" and "username" to be displayed on result.html
            modelAndView.addObject("taskCount", userWithTask.getTasks().size());
            modelAndView.addObject("username", userWithTask.getUserName());
            // set status code to 200 - Success
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (ParseException e) {
            // display error.html
            modelAndView.setViewName("error");
            // set status code to 400 - Bad Request
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (TaskUpdateException e) {
            // display error.html
            modelAndView.setViewName("error");
            // set status code to 500 - Internal Server Error
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return modelAndView;
    }
}