package com.Restaurant.Restaurant.filter;


import com.Restaurant.Restaurant.ServiceImplementation.UserDetailsServiceImpl;
import com.Restaurant.Restaurant.utils.JWTService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class JWTFilter extends OncePerRequestFilter {

	@Autowired
	private JWTService jwtService;

	@Autowired
	private ApplicationContext context;

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authHeader = request.getHeader("Authorization");
		String token = null;
		String username = null;

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			log.debug("Extracted username from token: {}", username);
			username = jwtService.extractUsernameFromToken(token);
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			Claims claims = jwtService.extractAllClaims(token);
			String role = claims.get("role", String.class);

//			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);

			UserDetails userDetails = context.getBean(UserDetailsServiceImpl.class).loadUserByUsername(username);

			if (jwtService.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null,
						List.of(authority));

				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
				log.info("JWT validated and authentication set for user: {}", username);
			}
		}

		filterChain.doFilter(request, response);
	}
}
