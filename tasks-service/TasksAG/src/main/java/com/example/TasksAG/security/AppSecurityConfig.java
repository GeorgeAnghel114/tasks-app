package com.example.TasksAG.security;

import com.example.TasksAG.service.ClientService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled=true,proxyTargetClass=true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private ClientService clientService;
    private final JWTTokenHelper jWTTokenHelper;
    private final AuthenticationEntryPoint authenticationEntryPoint;

    public AppSecurityConfig(ClientService clientService, JWTTokenHelper jWTTokenHelper, AuthenticationEntryPoint authenticationEntryPoint) {
        this.clientService = clientService;
        this.jWTTokenHelper = jWTTokenHelper;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint).and()
                .authorizeRequests(
                        (request) -> request.antMatchers(
                                "/",
                                "/api/client",
                                "/api/client/**",
                                "/api/client/register",
                                "/api/client/login").permitAll()
                )
                .authorizeRequests((request) -> request.antMatchers(
                                    "/api/task/**",
                                "/api/task/get-tasks/**",
                                "/api/task/add-task",
                                "/api/task/all-tasks",
                                "/api/task/update-task",
                                "/api/task/update-task/*",
                                "/api/client/all-clients",
                                "/api/task/search",
                                "/delete-task/*"
                        ).hasRole("USER")
                        .anyRequest().authenticated())
                .addFilterBefore(new JWTAuthenticationFilter(clientService, jWTTokenHelper),
                        UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable().cors().and().headers().frameOptions().disable();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}