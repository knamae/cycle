package jp.gr.java_conf.hungrywalker.dxo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.gr.java_conf.hungrywalker.entity.TaskRecordEntity;
import jp.gr.java_conf.hungrywalker.helper.CalendarHelper;
import jp.gr.java_conf.hungrywalker.helper.CipherHelper;
import jp.gr.java_conf.hungrywalker.web.task.record.TaskRecordForm;

@Component
public class TaskRecordDxo
{
    @Autowired
    CalendarHelper calendarHelper;

    @Autowired
    CipherHelper cipherHelper;

    public TaskRecordEntity convertFormToEntity(TaskRecordForm taskRecordForm)
    {
        TaskRecordEntity taskRecordEntity = new TaskRecordEntity();
        BeanUtils.copyProperties(taskRecordForm, taskRecordEntity, "taskId, executedDate");

        String _taskId = this.cipherHelper.decypt(taskRecordForm.getTaskId());
        taskRecordEntity.setTaskId(Long.valueOf(_taskId));

        taskRecordEntity
                .setExecutedDate(this.calendarHelper.convertDate(taskRecordForm.getExecutedDate()));
        taskRecordEntity.setExecuted(Boolean.TRUE);
        taskRecordEntity.setDeleted(Boolean.FALSE);

        return taskRecordEntity;
    }
}
