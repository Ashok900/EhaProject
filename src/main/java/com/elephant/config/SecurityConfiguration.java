package com.elephant.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.elephant.jwtauthentication.security.jwt.JwtAuthEntryPoint;
import com.elephant.jwtauthentication.security.jwt.JwtAuthTokenFilter;
import com.elephant.jwtauthentication.security.services.UserDetailsServiceImpl;


@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(
		prePostEnabled = true
)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	
	   @Autowired
	    UserDetailsServiceImpl userDetailsService;

	    @Autowired
	    private JwtAuthEntryPoint unauthorizedHandler;

	    @Bean
	    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
	        return new JwtAuthTokenFilter();
	    }

	    @Override
	    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
	        authenticationManagerBuilder
	                .userDetailsService(userDetailsService)
	                .passwordEncoder(passwordEncoder());
	    }

	    @Bean
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		

		http.cors().and().csrf().disable().
        authorizeRequests()
        .antMatchers("/v1/**","/v2/**", "/swagger-resources/configuration/ui", "/swagger-resources", "/swagger-resources/configuration/security", "/swagger-ui.html", "/webjars/**").permitAll()
        .anyRequest().authenticated()
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		 http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    
	}
	}




