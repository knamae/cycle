package jp.gr.java_conf.hungrywalker.web.task.record;

public class TaskRecordForm
{
    private Long taskId;

    private String memo;

    private String executedDate;

    public Long getTaskId()
    {
        return this.taskId;
    }

    public void setTaskId(Long taskId)
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
