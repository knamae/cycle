package jp.gr.java_conf.hungrywalker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import jp.gr.java_conf.hungrywalker.entity.MemberEntity;

@EnableJpaAuditing
@Configuration
public class JpaConfig
{
    @Bean
    public AuditorAware<Long> auditorAware()
    {
        return new SecurityAuditor();
    }

    public static class SecurityAuditor implements AuditorAware<Long>
    {
        @Override
        public Long getCurrentAuditor()
        {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated())
            {
                return null;
            }
            MemberEntity memberEntity = (MemberEntity) authentication.getPrincipal();

            return memberEntity.getId();
        }
    }
}
