package ibf2022.paf.assessment.server;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.models.User;
import ibf2022.paf.assessment.server.models.UserWithTask;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class DeserUtils {
    
    //Contains serialization and deserialization utils
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    //Function to deserialize a String into a UserWithTask
    public static UserWithTask toUserWithTask(String payload) throws ParseException {
        
        UserWithTask userWithTask = new UserWithTask();
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        String[] pairs = payload.split("&");

        // Parse each name-value pair and add it to the MultiValueMap object
        for (String pair : pairs) {
            String[] parts = pair.split("=");
            String name = parts[0];
            String value = parts[1];
            formData.add(name, value);
        }

        String userName = formData.getFirst("username");
        userWithTask.setUser(userName);
        Integer taskInt = 0;
        ArrayList<Task> tasks = new ArrayList<Task>();
        while (true) {
            String descriptionKey = String.format("description-%d", taskInt);
            String priorityKey = String.format("priority-%d", taskInt);
            String dueDateKey = String.format("dueDate-%d", taskInt);
            if (formData.containsKey(descriptionKey) && formData.containsKey(priorityKey) && formData.containsKey(dueDateKey)) {
                Task task = new Task();
                String taskDescription = formData.getFirst(descriptionKey);
                String taskPriority = formData.getFirst(priorityKey);
                String taskDueDateString = formData.getFirst(dueDateKey);
                Date taskDueDate = dateFormat.parse(taskDueDateString);
                task.setDescription(taskDescription);
                task.setPriority(taskPriority);
                task.setDueDate(taskDueDate);
                tasks.add(task);
                taskInt++;
               
            } else {
                break;
            }
        }
        userWithTask.setTasks(tasks);
        return userWithTask;
    }

    public static User toUser(SqlRowSet rs) {
        User user = new User();
        user.setUserId(rs.getString("user_id"));
        user.setName(rs.getString("name"));
        user.setUsername(rs.getString("username"));
        return user;
    }

}