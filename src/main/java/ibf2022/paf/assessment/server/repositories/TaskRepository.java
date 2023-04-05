package ibf2022.paf.assessment.server.repositories;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.models.TaskUpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

// TODO: Task 6

@Repository
public class TaskRepository {

    public static final String SQL_INSERT_TASK = "insert into task (task_id, description, priority, due_date, user_id) values (?, ?, ?, ?, ?)";

    @Autowired
    private JdbcTemplate template;

    public String insertTask(Task task, String userId) throws TaskUpdateException {

        String taskId = UUID.randomUUID().toString().substring(0, 8);

        int rowsInserted = template.update(SQL_INSERT_TASK, taskId, task.getDescription(), task.getPriority(), task.getDueDate(), userId);
        // check if task already exists
        if (rowsInserted > 0) {
            return taskId;
        } else {
            // if task is not inserted successfully
            TaskUpdateException newException = new TaskUpdateException();
            newException.setTaskInfo(task);
            throw newException;
        }

    }

}
