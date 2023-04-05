package ibf2022.paf.assessment.server;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.models.UserWithTask;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;

public class DeserUtils {

    //Contains serialization and deserialization utils
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    //Function to deserialize a String into a UserWithTask object
    public static UserWithTask toUserWithTask(String jsonStr) throws ParseException {
    
        UserWithTask userWithTask = new UserWithTask();
        JsonReader reader = Json.createReader(new StringReader(jsonStr));
        JsonObject json = reader.readObject();
        String userName = json.getString("username");
        userWithTask.setUser(userName);
        Integer taskInt = 0;
        ArrayList<Task> tasks = new ArrayList<Task>();
        while (true) {
            String descriptionKey = String.format("description-%d", taskInt);
            String priorityKey = String.format("priority-%d", taskInt);
            String dueDateKey = String.format("dueDate-%d", taskInt);
            if (json.containsKey(descriptionKey) && json.containsKey(priorityKey) && json.containsKey(dueDateKey)) {
                Task task = new Task();
                String taskDescription = json.getString(descriptionKey);
                String taskPriority = json.getString(priorityKey);
                String taskDueDateString = json.getString(dueDateKey);
                Date taskDueDate = dateFormat.parse(taskDueDateString);
                task.setDescription(taskDescription);
                task.setPriority(taskPriority);
                task.setDueDate(taskDueDate);
                tasks.add(task);
            } else {
                break;
            }
        }
        userWithTask.setTasks(tasks);
        return userWithTask;
    }

}