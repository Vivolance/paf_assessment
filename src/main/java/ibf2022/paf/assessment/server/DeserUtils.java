package ibf2022.paf.assessment.server;

import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import ibf2022.paf.assessment.server.models.Task;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

class DeserUtils {
    /**
     * Contains serialization and deserialization utils
     */

    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static Task toTask(String jsonStr) {
        /**
         * Function to deserialize a Task String to a Task object
         */
        Task task = new Task();
        JsonReader reader = Json.createReader(new StringReader(jsonStr));
        JsonObject json = reader.readObject();
        task.setDescription(json.getString("description"));
        task.setPriority(json.getString("priority"));
        task.setDueDate(json.getString("dueDate"));
        return task;
    }

}
