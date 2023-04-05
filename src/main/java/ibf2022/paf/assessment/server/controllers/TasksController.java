package ibf2022.paf.assessment.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ibf2022.paf.assessment.server.models.User;
import ibf2022.paf.assessment.server.repositories.UserRepository;

// TODO: Task 4, Task 8

@Controller
@RequestMapping(path="/api")
public class TasksController {

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/save-user")
    public String saveUser(@ModelAttribute("user") User user, Model model) {
        String userId = userRepo.insertUser(user);
        if (userId != null) {
            return "result";
        } else {
            return "error";
        }
    }





    
}
