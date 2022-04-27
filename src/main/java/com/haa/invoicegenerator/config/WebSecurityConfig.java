package com.haa.invoicegenerator.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        UserBuilder users = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(users.username("nazeer").password("123456").roles("EMPLOYEE", "MANAGER"))
                .withUser(users.username("ansari").password("123456").roles("EMPLOYEE", "LIMITED"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String[] staticResources = {
                "/css/**",
                "/images/**",
                "/js/**",
        };

        http.authorizeRequests()
                .antMatchers(staticResources)
                .permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers("/").hasRole("EMPLOYEE")// saveDefect
                .antMatchers("/customer/**").hasRole("EMPLOYEE")
                .antMatchers("/product/**").hasRole("EMPLOYEE")
                .antMatchers("/**").hasRole("MANAGER")
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authLogin")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied");
    }
}
