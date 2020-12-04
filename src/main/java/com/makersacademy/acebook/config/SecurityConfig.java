package com.makersacademy.acebook.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableAutoConfiguration
/* @EnableWebSecurity enables Spring Security's web security support and provids
Spring MVC integration.
It also extends WebSecurityConfigurerAdapter and overrides a couple of its methods
to set some specifics of the web sec config.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT email AS principal, password AS credentials, true FROM users WHERE " +
                        "email=?")
                .authoritiesByUsernameQuery("SELECT user_email AS principal, role_name AS role FROM user_roles " +
                        "where user_email=?")
                .passwordEncoder(passwordEncoder()).rolePrefix("ROLE_");
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    /* The below method defines which URL paths should be secured and which shouldnt.
    Specifically '/register', '/login' do not require authentication.
    All other paths must be authenticated.

    When a user successfully logs in, they are redirected to the previously requested page that required
    authentication. There is a custom /login page (which is specified by loginPage()), and everyone is allowed
    to view it.
     */
    protected void configure(HttpSecurity http) throws Exception {

        //Required to allow logged in users to make post requests, BAD PRACTICE!!
        //Look into CSRF tokens
        http.sessionManagement()
                .sessionCreationPolicy((SessionCreationPolicy.ALWAYS));

        http.authorizeRequests().antMatchers("/register", "/login", "/built/*").permitAll()
          
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll();
    }


}
