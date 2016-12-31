package jp.gr.java_conf.hungrywalker.web.task;

public class TaskModifyForm extends AbstractTaskForm
{
    private Long taskId;

    public Long getTaskId()
    {
        return taskId;
    }

    public void setTaskId(Long taskId)
    {
        this.taskId = taskId;
    }
}
