package com.vallete.portfolio.backendjava.config;

import com.vallete.portfolio.backendjava.controller.UserControllerInterface;
import org.springframework.context.annotation.Bean;
import com.vallete.portfolio.backendjava.model.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

import java.util.Collections;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

	private final JwtAuthFilter jwtAuthFilter;
	private final UserControllerInterface userController;

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public UserDetailsService userDetailsService(){
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
				User user = userController.getUserByEmail(email);
				UserDetails userDetails =
						new org.springframework.security.core.userdetails.User(
								user.getEmail(),
								user.getPassword(),
								Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))
						);
				return userDetails;
			}
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
		
	}


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
			//.csrf().disable()
			.authorizeRequests()
			.requestMatchers("**/user/**") //antMatchers is deprecated
			.permitAll()
			.anyRequest().authenticated()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authenticationProvider(authenticationProvider())
			.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
			.httpBasic()
			;

//		http
//			//.csrf().disable()
//			.authorizeRequests()
//			.requestMatchers("**/user/**") //antMatchers is deprecated
//			.permitAll()
//			.anyRequest().authenticated()
//			.and()
//			.sessionManagement()
//			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//			.and()
//			.authenticationProvider(authenticationProvider())
//			.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//			.httpBasic()
//			;
		
		return http.build();
	}

	@Bean
	public AuthenticationProvider authenticationProvider(){
		final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
//	@Bean
//	public WebSecurityCustomizer  webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers("/portfolio/user/authenticate", "/portfolio/user/register");
//	}
}
