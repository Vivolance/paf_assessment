package ibf2022.paf.assessment.server.services;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ibf2022.paf.assessment.server.models.TaskUpdateException;
import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.models.User;
import ibf2022.paf.assessment.server.repositories.TaskRepository;
import ibf2022.paf.assessment.server.repositories.UserRepository;

// TODO: Task 7

@Service
public class TodoService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional(rollbackFor = TaskUpdateException.class)
    public String upsertTask(ArrayList<Task> tasks, User user) throws TaskUpdateException {
        /**
         * Used to insert a list of Tasks for a particular user into table `tasks`
         *
         * If user does not exist, create the user before inserting the tasks
         *
         * Since the method updates multiple tasks, and inserting multiple records
         *
         * We will ensure all are successful or not at all if any fails, by annotating this with a @Transactional
         *
         * Returns a Transaction ID, representing the success of the transaction
         */
        String txId = UUID.randomUUID().toString().substring(0, 8);
        createUserIfNotExists(user);

        String userId = user.getUserId();

        // PS: This shit ain't ideal; we should be using either AsyncIO (concurrently fire requests), or Parallelism (ThreadPoolExecutor) here.
        // But screw it :D lets keep it simple
        for (Task task: tasks) {
            taskRepository.insertTask(task, userId);
        }
        return txId;
    }

    public void createUserIfNotExists(User user) {
        /**
         * Checks if a user exists.
         *
         * If it doesn't, create one
         */
        Optional<User> foundUser = userRepository.findUserByUsername(user.getUsername());
        if (foundUser.isEmpty()) {
            userRepository.insertUser(user);
        }
    }
}


