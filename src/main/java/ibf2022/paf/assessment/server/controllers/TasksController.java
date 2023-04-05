package ibf2022.paf.assessment.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ibf2022.paf.assessment.server.services.TodoService;

// TODO: Task 4, Task 8

@Controller
@RequestMapping(path="/")
public class TasksController {

    @Autowired
    TodoService todoService;

    @PostMapping(path = "/task", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postTransfer(@RequestBody String payload) {
        System.out.printf(">>> payload: %s\n", payload);
        return ResponseEntity.ok(payload);
    }
}

