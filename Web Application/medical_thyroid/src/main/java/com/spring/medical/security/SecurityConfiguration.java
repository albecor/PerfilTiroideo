package com.spring.medical.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.spring.medical.dao.DAO;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;

	@Autowired
	DAO dao;

	/*
	 * @Autowired private SimpleAuthenticationSuccessHandler successHandler;
	 */

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/updateYourself")
				.access("hasRole('PERSONAL') or hasRole('ADMIN') or hasRole('PATIENT') or hasRole('LAB') or hasRole('TEMPORAL')")
				.antMatchers("/te/**").access("hasRole('TEMPORAL')").antMatchers("/ad/**").access("hasRole('ADMIN')")
				.antMatchers("/pe/**").access("hasRole('PERSONAL')").antMatchers("/pa/**").access("hasRole('PATIENT')")
				.antMatchers("/la/**").access("hasRole('LAB')").antMatchers("/pp/**")
				.access("hasRole('PATIENT') or hasRole('PERSONAL')").antMatchers("/ch/**")
				.hasAuthority("CHANGE_PASSWORD_PRIVILEGE").and().formLogin()
				.successHandler(savedRequestAwareAuthenticationSuccessHandler()).loginPage("/login")
				.failureUrl("/login?error").loginProcessingUrl("/auth/login_check").usernameParameter("username")
				.passwordParameter("password").and().logout().logoutSuccessUrl("/login?logout").and().csrf().and()
				.exceptionHandling().accessDeniedPage("/Access_Denied").and().rememberMe()
				.tokenRepository(persistentTokenRepository()).tokenValiditySeconds(1209600);
		http.sessionManagement().maximumSessions(2).expiredUrl("/login?expired");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public AuthenticationTrustResolver getAuthenticationTrustResolver() {
		return new AuthenticationTrustResolverImpl();
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl token = new JdbcTokenRepositoryImpl();
		token.setDataSource(dao.getDatasource());
		return token;
	}

	@Bean
	public SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler() {
		SavedRequestAwareAuthenticationSuccessHandler auth = new SavedRequestAwareAuthenticationSuccessHandler();
		auth.setTargetUrlParameter("targetUrl");
		return auth;
	}
}
