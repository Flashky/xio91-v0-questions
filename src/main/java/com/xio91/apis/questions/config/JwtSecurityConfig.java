package com.xio91.apis.questions.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

	    @Override
	protected void configure(HttpSecurity http) throws Exception {
	    http
	      .authorizeRequests(authz -> authz
	        .antMatchers(HttpMethod.GET, "/questions**").permitAll()
	        .antMatchers(HttpMethod.POST, "/questions").hasAuthority("SCOPE_write_questions")
	        .antMatchers(HttpMethod.PUT, "/questions").hasAuthority("SCOPE_write_questions")
	        )
	      .oauth2ResourceServer(oauth2 -> oauth2.jwt());
	}
    
}
