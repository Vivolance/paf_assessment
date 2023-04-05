package ibf2022.paf.assessment.server.models;

import java.util.ArrayList;

public class UserWithTask {
    private String userName;
    private ArrayList<Task> tasks;

    public String getUserName() {
        return this.userName;
    }

    public void setUser(String userName) {
        this.userName = userName;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


}
