package com.testSpring.config;

import com.testSpring.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider authProvide = new DaoAuthenticationProvider();
        authProvide.setPasswordEncoder(this.passwordEncoder());
        authProvide.setUserDetailsService(this.userDetailsService());
        auth.authenticationProvider(authProvide);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().
                disable()
            .authorizeRequests()
                .antMatchers("/registration", "/login", "/js/**", "/styles/**", "/img/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/myday")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }
}
