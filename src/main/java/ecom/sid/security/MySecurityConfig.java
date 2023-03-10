package ecom.sid.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class MySecurityConfig  extends
WebSecurityConfigurerAdapter{
	
	
	  @Autowired 
	  private CustomUserDetailsService userDetailsService;
	 
	
	  
	  
	  @Override protected void configure(HttpSecurity http) throws Exception {
	  
	//  http.authorizeHttpRequests().anyRequest().permitAll();
	  http.authorizeHttpRequests().antMatchers("/index").authenticated().anyRequest()
	  .permitAll()
	  .and().formLogin().loginPage("/login")
	  .usernameParameter("email")
	  .permitAll();
	  
	  
	  }
	  
	  @Bean 
	 public PasswordEncoder passwordEncoder() { 
	 return new BCryptPasswordEncoder();
	 }
	  
	  @Override 
	  protected void configure(AuthenticationManagerBuilder auth) 
			  throws Exception {
	  auth.userDetailsService(userDetailsService).
	  passwordEncoder(passwordEncoder());
	  }
}
