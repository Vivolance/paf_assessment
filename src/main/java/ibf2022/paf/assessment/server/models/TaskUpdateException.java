package ibf2022.paf.assessment.server.models;

public class TaskUpdateException extends Exception {

    private Task taskInfo;

    public TaskUpdateException() {
        super();
    }

    public TaskUpdateException(String msg) {
        super(msg);
    }

    public void setTaskInfo(Task taskInfo) { this.taskInfo = taskInfo; }
    public Task getTaskInfo() { return this.taskInfo; }

}
