package jp.gr.java_conf.hungrywalker.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.gr.java_conf.hungrywalker.entity.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Long>
{
    public List<TaskEntity> findByMemberId(Long memberId);

    @Query("SELECT t FROM TaskEntity t WHERE t.nextLimitDate = :targetDate AND t.status = 1 AND t.notifyStatus = 1")
    public List<TaskEntity> findNotificationList(@Param("targetDate") Date targetDate);
}
