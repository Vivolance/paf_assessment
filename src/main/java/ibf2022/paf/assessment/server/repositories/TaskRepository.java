package ibf2022.paf.assessment.server.repositories;

import org.springframework.stereotype.Repository;

import ibf2022.paf.assessment.server.models.Task;

// TODO: Task 6

@Repository
public class TaskRepository {

    public String insertTask(Task task) {
        
        int taskInserted = template.update(SQL_INSERT_USER, userId, user.getUsername(), user.getName());
        // check if task already exists
        if (taskInserted > 0) {
            return task;
        } else {
            // if task is not inserted successfully
            return null;
        }

    }
}
