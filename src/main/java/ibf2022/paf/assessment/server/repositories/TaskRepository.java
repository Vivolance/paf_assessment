package ibf2022.paf.assessment.server.repositories;

import org.springframework.stereotype.Repository;

import ibf2022.paf.assessment.server.models.Task;

// TODO: Task 6

@Repository
public class TaskRepository {

    public String insertTask(Task task) {

        String userId = UUID.randomUUID().toString().substring(0, 8);
        
        int rowsInserted = template.update(SQL_INSERT_USER, userId, user.getUsername(), user.getName());
        // check if user already exists
        if (rowsInserted > 0) {
            return userId;
        } else {
            // if user is not inserted successfully
            return null;
        }

    }
}
