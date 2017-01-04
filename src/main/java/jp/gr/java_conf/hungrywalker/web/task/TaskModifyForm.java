package jp.gr.java_conf.hungrywalker.web.task;

public class TaskModifyForm extends AbstractTaskForm
{
    private String taskId;

    public String getTaskId()
    {
        return this.taskId;
    }

    public void setTaskId(String taskId)
    {
        this.taskId = taskId;
    }
}
