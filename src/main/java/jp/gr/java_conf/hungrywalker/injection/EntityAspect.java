package jp.gr.java_conf.hungrywalker.injection;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import jp.gr.java_conf.hungrywalker.entity.BaseEntity;
import jp.gr.java_conf.hungrywalker.entity.MemberEntity;

/**
 * エンティティ関連のAspect
 *
 * @author knamae
 */
@Aspect
@Component
public class EntityAspect
{
    /**
     * エンティティ生成時にログインメンバーidを設定
     *
     * @param joinPoint
     * @param returnValue
     */
    @AfterReturning(pointcut = "execution( * jp.gr.java_conf.hungrywalker.dxo.*.convert*(..))", returning = "returnValue")
    public void setMemberId(JoinPoint joinPoint, Object returnValue)
    {
        if (returnValue instanceof BaseEntity)
        {
            BaseEntity baseEntity = (BaseEntity) returnValue;

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated())
            {
                return;
            }
            MemberEntity memberEntity = (MemberEntity) authentication.getPrincipal();

            baseEntity.setMemberId(memberEntity.getId());
        }
    }
}
