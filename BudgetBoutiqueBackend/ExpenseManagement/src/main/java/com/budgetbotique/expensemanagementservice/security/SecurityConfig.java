package com.budgetbotique.expensemanagementservice.security;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	 
	 private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.csrf().disable()
	            .authorizeRequests()
	            .antMatchers("/api/expenses/add").permitAll()
	            .antMatchers("/api/expenses/user/**").permitAll()
	            .antMatchers("/api/expenses/user/filterExpenses/**").permitAll()
	            .antMatchers("/api/expenses/edit/**").authenticated()
	            .anyRequest().authenticated()
	            .and()
	            .httpBasic()
	            .and()
	            .exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
	                logger.error("Authentication failed: {}", authException.getMessage());
	                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed");
	            })
	            .and()
	            .logout().logoutSuccessHandler((request, response, authentication) -> {
	                logger.info("Logout successful");
	                response.setStatus(HttpServletResponse.SC_OK);
	            });
	    }

}

