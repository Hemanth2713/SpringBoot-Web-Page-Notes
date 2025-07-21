//package com.Restaurant.Restaurant.Config;
//
//import com.Restaurant.Restaurant.ServiceImplementation.UserDetailsServiceImpl;
//import com.Restaurant.Restaurant.filter.JWTFilter;
//import jakarta.servlet.Filter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@Slf4j
//public class SecurityConfig {
//
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		return http.csrf(AbstractHttpConfigurer::disable)
//				.cors(Customizer.withDefaults())
//				.authorizeHttpRequests(auth -> auth
//						// Publicly accessible APIs
//						.requestMatchers(
//								"/api/user/register",
//								"/api/user/admin/register",
//								"/api/user/login",
//								"/swagger-ui/**",
//								"/v3/api-docs/**",
//								"/swagger-ui.html"
//						).permitAll()
//
//						// USER Role
//						.requestMatchers(
//								"/api/address/**",
//								"/api/cart-items/**",
//								"/api/orders",
//								"/api/orders/cancel",
//								"/api/orders/getOrderById/**",
//								"/api/orders/ordersByUserId/**",
//								"/api/users/updateUser",
//								"/api/users/changePassword",
//								"/api/menu/**",
//								"/api/menuItem/**"
//						).hasRole("USER")
//
//						// ADMIN Role
//						.requestMatchers(
//								"/api/users/allUser",
//								"/api/orders/getAll",
//								"/api/orders/updateStatus/**"
//						).hasRole("ADMIN")
//
//						// Any other requests need to be authenticated
//						.anyRequest().authenticated()
//				)
//				.httpBasic(Customizer.withDefaults())
//				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//				.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
//				.build();
//	}
//
//	@Bean
//	public AuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setPasswordEncoder(passwordEncoder());
//		provider.setUserDetailsService(userDetailsServiceImpl());
//		return provider;
//	}
//
//	@Bean
//	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//		return config.getAuthenticationManager();
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	@Bean
//	@Lazy
//	public UserDetailsServiceImpl userDetailsServiceImpl() {
//		return new UserDetailsServiceImpl();
//	}
//
//	@Bean
//	@Lazy
//	public Filter jwtFilter() {
//		return new JWTFilter();
//	}
//}

package com.Restaurant.Restaurant.Config;

import com.Restaurant.Restaurant.ServiceImplementation.UserDetailsServiceImpl;
import com.Restaurant.Restaurant.filter.JWTFilter;
import jakarta.servlet.Filter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@Slf4j
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(AbstractHttpConfigurer::disable)
				.cors(Customizer.withDefaults())
				.authorizeHttpRequests(auth -> auth

						// Public endpoints (no login required)
						.requestMatchers(
								"/api/user/register",
								"/api/user/admin/register",
								"/api/user/login",
								"/swagger-ui/**",
								"/v3/api-docs/**",
								"/swagger-ui.html"
						).permitAll()

						// Allow GET access to menu and menu items for USER and ADMIN
						.requestMatchers(HttpMethod.GET, "/api/menu/**").hasAnyRole("USER", "ADMIN")
						.requestMatchers(HttpMethod.GET, "/api/menuItem/**").hasAnyRole("USER", "ADMIN")

						// Only ADMIN can modify menus
						.requestMatchers(HttpMethod.POST, "/api/menu/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.PUT, "/api/menu/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/api/menu/**").hasRole("ADMIN")

						// Only ADMIN can modify menu items
						.requestMatchers(HttpMethod.POST, "/api/menuItem/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.PUT, "/api/menuItem/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/api/menuItem/**").hasRole("ADMIN")

						// USER-specific functionality
						.requestMatchers(
								"/api/address/**",
								"/api/cart-items/**",
								"/api/orders",
								"/api/orders/cancel",
								"/api/orders/getOrderById/**",
								"/api/orders/ordersByUserId/**",
								"/api/users/updateUser",
								"/api/users/changePassword"
						).hasRole("USER")

						// ADMIN-specific functionality
						.requestMatchers(
								"/api/users/allUser",
								"/api/orders/getAll",
								"/api/orders/updateStatus/**"
						).hasRole("ADMIN")

						// Any other requests must be authenticated
						.anyRequest().authenticated()
				)
				.httpBasic(Customizer.withDefaults())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(userDetailsServiceImpl());
		return provider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Lazy
	public UserDetailsServiceImpl userDetailsServiceImpl() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	@Lazy
	public Filter jwtFilter() {
		return new JWTFilter();
	}
}
