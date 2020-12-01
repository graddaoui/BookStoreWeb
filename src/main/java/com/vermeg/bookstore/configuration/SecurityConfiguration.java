package com.vermeg.bookstore.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private DataSource dataSource;
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/").permitAll() 
				.antMatchers("/login").permitAll() 
				.antMatchers("/role/**").hasAnyAuthority("ADMIN")
				.antMatchers("/accounts/**").hasAnyAuthority("ADMIN")
				.antMatchers("/registration").permitAll() 

				
				.antMatchers("/orders/add/").hasAnyAuthority("USER","ADMIN")
				.antMatchers("/orders/**").hasAnyAuthority("ADMIN")
				
                .antMatchers("/book/**").hasAnyAuthority("ADMIN").anyRequest()

				.authenticated().and().csrf().disable().formLogin() 

				.loginPage("/login").failureUrl("/login?error=true") 

				.defaultSuccessUrl("/home") 
				.usernameParameter("email") 
				.passwordParameter("password").and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
																															
																															
																															
																															
				.logoutSuccessUrl("/login").and().exceptionHandling()

				.accessDeniedPage("/403");
	}

	// laisser l'accès aux ressources
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

}