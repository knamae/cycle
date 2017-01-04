package jp.gr.java_conf.hungrywalker.web.task.record;

public class TaskRecordForm
{
    private String taskId;

    private String memo;

    private String executedDate;

    public String getTaskId()
    {
        return this.taskId;
    }

    public void setTaskId(String taskId)
    {
        this.taskId = taskId;
    }

    public String getMemo()
    {
        return this.memo;
    }

    public void setMemo(String memo)
    {
        this.memo = memo;
    }

    public String getExecutedDate()
    {
        return this.executedDate;
    }

    public void setExecutedDate(String executedDate)
    {
        this.executedDate = executedDate;
    }
}
