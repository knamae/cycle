package jp.gr.java_conf.hungrywalker.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * タスク履歴エンティティ
 *
 * @author knamae
 */
@Entity
@Table(name = "task_record")
@EntityListeners(AuditingEntityListener.class)
public class TaskRecordEntity extends BaseEntity
{
    private static final long serialVersionUID = -5052959134431811285L;

    /** タスクID */
    private Long taskId;

    /** メモ */
    private String memo;

    /** 実施有無 */
    private Boolean executed;

    /** 実施日時 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date executedDate;

    /** 実施間隔 */
    private Integer intervalDay;

    /** 削除状態 */
    private Boolean deleted;

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

    public Boolean getExecuted()
    {
        return this.executed;
    }

    public void setExecuted(Boolean executed)
    {
        this.executed = executed;
    }

    public Date getExecutedDate()
    {
        return this.executedDate;
    }

    public void setExecutedDate(Date executedDate)
    {
        this.executedDate = executedDate;
    }

    public Integer getIntervalDay()
    {
        return this.intervalDay;
    }

    public void setIntervalDay(Integer intervalDay)
    {
        this.intervalDay = intervalDay;
    }

    public Boolean getDeleted()
    {
        return this.deleted;
    }

    public void setDeleted(Boolean deleted)
    {
        this.deleted = deleted;
    }

}
