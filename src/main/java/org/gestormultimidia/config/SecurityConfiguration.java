package org.gestormultimidia.config;

import org.gestormultimidia.dao.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebMvcSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Override
	protected void configure (HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/static/**").permitAll()
			.antMatchers("imagens/form").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/imagens").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/imagens").hasRole("ADMIN")
		.antMatchers("/").permitAll()
		.anyRequest().authenticated()
		.and().formLogin();

	}  

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioDAO)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
}
