package ibf2022.paf.assessment.server.repositories;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.models.TaskUpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

// TODO: Task 6

@Repository
public class TaskRepository {

    public static final String SQL_INSERT_TASK = "insert into task (description, priority, due_date, user_id) values (?, ?, ?, ?)";

    @Autowired
    private JdbcTemplate template;

    public void insertTask(Task task, String userId) throws TaskUpdateException {
        int rowsInserted = template.update(SQL_INSERT_TASK, task.getDescription(), task.getPriority(), task.getDueDate(), userId);
        // check if task already exists
        if (rowsInserted == 0) {
            // if task is not inserted successfully
            TaskUpdateException newException = new TaskUpdateException();
            newException.setTaskInfo(task);
            throw newException;
        }
    }

}