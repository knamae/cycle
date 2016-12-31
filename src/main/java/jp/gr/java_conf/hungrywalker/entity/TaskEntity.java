package jp.gr.java_conf.hungrywalker.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * タスクエンティティ
 *
 * @author knamae
 */
@Entity
@Table(name = "task")
@EntityListeners(AuditingEntityListener.class)
public class TaskEntity extends BaseEntity
{
    private static final long serialVersionUID = 8387310659297623088L;

    /** タイトル */
    private String title;

    /** 内容 */
    private String description;

    /** タスクの状態 */
    private Integer status;

    /** 実施間隔(設定) */
    private Integer settingInterval;

    /** 実施間隔(実績) */
    private Integer averageInterval;

    /** 次回実施日時 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date nextLimitDate;

    /** 実施回数 */
    private Integer executedCount;

    /** 通知を行うか */
    private Boolean doNotify;

    /** 通知状態 */
    private Integer notifyStatus;

    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public TaskStatus getStatus()
    {
        return TaskStatus.parse(this.status);
    }

    public void setStatus(TaskStatus status)
    {
        this.status = Integer.valueOf(status.getValue());
    }

    public Integer getSettingInterval()
    {
        return this.settingInterval;
    }

    public void setSettingInterval(Integer settingInterval)
    {
        this.settingInterval = settingInterval;
    }

    public Integer getAverageInterval()
    {
        return this.averageInterval;
    }

    public void setAverageInterval(Integer averageInterval)
    {
        this.averageInterval = averageInterval;
    }

    public Date getNextLimitDate()
    {
        return this.nextLimitDate;
    }

    public void setNextLimitDate(Date nextLimitDate)
    {
        this.nextLimitDate = nextLimitDate;
    }

    public Integer getExecutedCount()
    {
        return this.executedCount;
    }

    public void setExecutedCount(Integer executedCount)
    {
        this.executedCount = executedCount;
    }

    public Boolean getDoNotify()
    {
        return this.doNotify;
    }

    public void setDoNotify(Boolean doNotify)
    {
        this.doNotify = doNotify;
    }

    public Integer getNotifyStatus()
    {
        return this.notifyStatus;
    }

    public void setNotifyStatus(Integer notifyStatus)
    {
        this.notifyStatus = notifyStatus;
    }

}
