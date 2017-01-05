package jp.gr.java_conf.hungrywalker.injection;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import jp.gr.java_conf.hungrywalker.entity.BaseEntity;
import jp.gr.java_conf.hungrywalker.entity.MemberEntity;
import jp.gr.java_conf.hungrywalker.entity.TaskEntity;
import jp.gr.java_conf.hungrywalker.helper.CipherHelper;

/**
 * サービス層関連のAspect
 *
 * @author knamae
 */
@Aspect
@Component
public class ServiceAspect
{
    @Autowired
    private CipherHelper cipherHelper;

    /**
     * 取得可能な情報かを確認
     *
     * @param joinPoint
     * @param returnValue
     */
    @AfterReturning(pointcut = "execution( * jp.gr.java_conf.hungrywalker.service.*.get*(..))", returning = "returnValue")
    public void checkMemberId(JoinPoint joinPoint, Object returnValue)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated())
        {
            return;
        }
        MemberEntity memberEntity = (MemberEntity) authentication.getPrincipal();
        Long memberId = memberEntity.getId();

        if (returnValue instanceof List<?>)
        {
            List<?> list = (List<?>) returnValue;

            for (Iterator<?> iterator = list.iterator(); iterator.hasNext();)
            {
                Object object = iterator.next();
                if (object instanceof BaseEntity)
                {
                    BaseEntity base = (BaseEntity) object;
                    if (!Objects.equals(base.getMemberId(), memberId))
                    {
                        iterator.remove();
                    }
                }
            }
        } else
        {
            if (returnValue instanceof BaseEntity)
            {
                BaseEntity base = (BaseEntity) returnValue;
                if (!Objects.equals(base.getMemberId(), memberId))
                {
                    returnValue = null;
                }
            }
        }
    }

    @AfterReturning(pointcut = "execution( * jp.gr.java_conf.hungrywalker.service.TaskService.get*(..))", returning = "returnValue")
    public void setTaskId(JoinPoint joinPoint, Object returnValue)
    {
        if (returnValue instanceof List<?>)
        {
            List<?> list = (List<?>) returnValue;
            for (Iterator<?> iterator = list.iterator(); iterator.hasNext();)
            {
                Object object = iterator.next();
                if (object instanceof TaskEntity)
                {
                    TaskEntity task = (TaskEntity) object;
                    task.setTaskId(this.cipherHelper.encrypt(String.valueOf(task.getId())));
                }
            }
        } else
        {
            if (returnValue instanceof TaskEntity)
            {
                TaskEntity task = (TaskEntity) returnValue;
                task.setTaskId(this.cipherHelper.encrypt(String.valueOf(task.getId())));
            }
        }
    }
}
