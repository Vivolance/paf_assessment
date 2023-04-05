package ibf2022.paf.assessment.server.repositories;

import org.springframework.stereotype.Repository;

import ibf2022.paf.assessment.server.models.Task;

// TODO: Task 6

@Repository
public class TaskRepository {

    public static final String SQL_INSERT_TASK = "INSERT INTO task (description, priority, due_date, user_id) VALUES (?, ?, ?, ?)";

    public void insertTask(Task task) {

        int count = template.queryForObject(SQL_INSERT_TASK, Integer.class, task.getDescription(), task.getDueDate(), task.getUserId());
    
        if (count == 0) {
            String insertSql = "INSERT INTO task (description, priority, due_date, user_id) VALUES (?, ?, ?, ?)";
            template.update(insertSql, task.getDescription(), task.getPriority(), task.getDueDate(), task.getUserId());
        } else {
            throw new IllegalArgumentException("Task already exists");
        }
    }
    

     
    
}
