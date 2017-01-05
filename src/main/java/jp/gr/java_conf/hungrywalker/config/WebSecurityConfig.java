package jp.gr.java_conf.hungrywalker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import jp.gr.java_conf.hungrywalker.web.LoginController;

@Configuration
public class WebSecurityConfig
{
    @EnableResourceServer
    @Configuration
    @Order(2)
    public static class OAuthResourceConfig extends ResourceServerConfigurerAdapter
    {
        static final String RESOURCE_ID = "my_resource_id";

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception
        {
            resources.resourceId(RESOURCE_ID);
        }

        @Override
        public void configure(HttpSecurity httpSecurity) throws Exception
        {
            httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET, "/api/**")
                    .access("#oauth2.hasScope('read')");
        }
    }

    @Configuration
    @Order(1)
    public static class SecurityConfigurerAdapter extends WebSecurityConfigurerAdapter
    {
        @Autowired
        UserDetailsService userDetailsService;

        @Override
        public void configure(WebSecurity webSecurity) throws Exception
        {
            webSecurity.ignoring().antMatchers("/css/**");
        }

        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception
        {
            httpSecurity.authorizeRequests().antMatchers("/oauth/**").authenticated().and()
                    .httpBasic().realmName("OAuth Server");

            httpSecurity.formLogin().loginPage(LoginController.PAGE)
                    .usernameParameter("mailAddress").passwordParameter("password").permitAll();
            httpSecurity.logout().permitAll();
            httpSecurity.authorizeRequests().anyRequest().authenticated();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder)
                throws Exception
        {
            authenticationManagerBuilder.userDetailsService(this.userDetailsService);
        }
    }
}
