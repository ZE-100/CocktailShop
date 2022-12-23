package com.z100.cocktailshop.server.config;

import com.z100.cocktailshop.server.security.filters.JwtAuthenticationFilter;
import com.z100.cocktailshop.server.security.filters.JwtAuthorizationFilter;
import com.z100.cocktailshop.server.security.services.JwtUserDetailsService;
import com.z100.cocktailshop.server.security.util.handler.JwtAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	private final JwtUserDetailsService jwtUserDetailsService;

	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	private final JwtAuthorizationFilter jwtAuthorizationFilter;

	private static final String[] AUTH_WHITELIST = new String[] {
			"/login",
			"/register"
	};

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		jwtAuthenticationFilter.setFilterProcessesUrl("/auth/login");
		jwtAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());

		http.csrf().disable()
				.authorizeRequests()
					.antMatchers(AUTH_WHITELIST).permitAll()
					.antMatchers("/user/**").hasRole("USER")
					.antMatchers("/admin/**").hasRole("ADMIN")
					.anyRequest().authenticated()
					.and()
				.formLogin()
					.loginPage("/login.html")
					.failureUrl("/misc/error.html")
					.and()
				.logout()
					.invalidateHttpSession(true)
					.clearAuthentication(true)
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
					.logoutSuccessUrl("/");
	}

	@Bean
	public PasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
