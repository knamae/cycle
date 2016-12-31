package jp.gr.java_conf.hungrywalker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.gr.java_conf.hungrywalker.entity.TaskRecordEntity;

public interface TaskRecordRepository extends JpaRepository<TaskRecordEntity, Long>
{
    public List<TaskRecordEntity> findByTaskId(Long taskId);

    @Query("SELECT tr FROM TaskRecordEntity tr WHERE tr.taskId = :taskId ORDER BY id DESC")
    public TaskRecordEntity findLatestByTaskId(@Param("taskId") Long taskId);
}
