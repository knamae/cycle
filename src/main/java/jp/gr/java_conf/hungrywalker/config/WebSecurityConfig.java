package jp.gr.java_conf.hungrywalker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
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
        httpSecurity.authorizeRequests().antMatchers("/**").authenticated().and().httpBasic()
                .realmName("OAuth Server");

        // httpSecurity.formLogin().loginPage(LoginController.PAGE).usernameParameter("mailAddress")
        // .passwordParameter("password").permitAll();
        // httpSecurity.logout().permitAll();
        //
        // httpSecurity.authorizeRequests().anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder)
            throws Exception
    {
        authenticationManagerBuilder.userDetailsService(this.userDetailsService);
    }
}
