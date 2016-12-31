package jp.gr.java_conf.hungrywalker.injection;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import jp.gr.java_conf.hungrywalker.entity.BaseEntity;
import jp.gr.java_conf.hungrywalker.entity.MemberEntity;

/**
 * サービス層関連のAspect
 *
 * @author knamae
 */
@Aspect
@Component
public class ServiceAspect
{
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
}
