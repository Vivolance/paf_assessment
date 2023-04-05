package ibf2022.paf.assessment.server.models;

import java.time.Instant;

// TODO: Task 4

public class Task {
    private String description;
    private String priority;
    private String dueDate;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return this.priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(String string) {
        this.dueDate = string;
    }
    


}
