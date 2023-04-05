package ibf2022.paf.assessment.server.models;

import java.util.Date;

//import java.time.Instant;

// TODO: Task 4

public class Task {
    private String description;
    private String priority;
    private Date dueDate;

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

    public Date getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}