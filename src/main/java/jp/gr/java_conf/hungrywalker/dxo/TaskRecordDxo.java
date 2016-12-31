package jp.gr.java_conf.hungrywalker.dxo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.gr.java_conf.hungrywalker.entity.TaskRecordEntity;
import jp.gr.java_conf.hungrywalker.helper.CalendarHelper;
import jp.gr.java_conf.hungrywalker.web.task.record.TaskRecordForm;

@Component
public class TaskRecordDxo
{
    @Autowired
    CalendarHelper calendarHelper;

    public TaskRecordEntity convertFormToEntity(TaskRecordForm taskRecordForm)
    {
        TaskRecordEntity taskRecordEntity = new TaskRecordEntity();
        BeanUtils.copyProperties(taskRecordForm, taskRecordEntity, "executedDate");

        taskRecordEntity
                .setExecutedDate(this.calendarHelper.convertDate(taskRecordForm.getExecutedDate()));
        taskRecordEntity.setExecuted(Boolean.TRUE);
        taskRecordEntity.setDeleted(Boolean.FALSE);

        return taskRecordEntity;
    }
}
