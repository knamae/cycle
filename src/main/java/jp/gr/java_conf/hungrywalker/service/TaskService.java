package jp.gr.java_conf.hungrywalker.service;

import java.util.List;

import jp.gr.java_conf.hungrywalker.entity.TaskEntity;
import jp.gr.java_conf.hungrywalker.entity.TaskRecordEntity;

public interface TaskService
{
    void add(TaskEntity taskEntity);

    TaskEntity get(Long taskId);

    List<TaskEntity> getListOrderByLimitDateAsc(Long memberId);

    void addRecord(TaskRecordEntity taskRecordEntity);

    List<TaskRecordEntity> getRecordList(Long taskId);
}
