package ibf2022.paf.assessment.server.services;

import ibf2022.paf.assessment.server.Utils;
import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.models.TaskUpdateException;
import ibf2022.paf.assessment.server.models.User;
import ibf2022.paf.assessment.server.repositories.TaskRepository;
import ibf2022.paf.assessment.server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

// TODO: Task 7

@Service
public class TodoService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional(rollbackFor = TaskUpdateException.class)
    public String upsertTask(ArrayList<Task> tasks, String userName) throws TaskUpdateException {
       
        String txId = UUID.randomUUID().toString().substring(0, 8);
        User user = getOrCreateAndInsertUser(userName);
        String userId = user.getUserId();
        
        
        for (Task task: tasks) {
            taskRepository.insertTask(task, userId);
        }
        return txId;
    }

    public User getOrCreateAndInsertUser(String userName) throws TaskUpdateException {
        
        Optional<User> foundUser = userRepository.findUserByUsername(userName);
        if (foundUser.isEmpty()) {
            User newUser = Utils.createNewUser(userName);
            userRepository.insertUser(newUser);
            return newUser;
        } else {
            return foundUser.get();
        }
    }
}