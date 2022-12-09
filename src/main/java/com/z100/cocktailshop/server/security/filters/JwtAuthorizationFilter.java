package com.z100.cocktailshop.server.security.filters;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.z100.cocktailshop.server.properties.JwtConstants;
import com.z100.cocktailshop.server.security.util.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Log4j2
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

	private final JwtTokenUtil tokenUtil;

	private JwtConstants constants;

	private final Predicate<String> isUnprotectedUrl = (req) ->
			constants.getUriwhitelist().stream().anyMatch(req::contains);


	public JwtAuthorizationFilter(JwtTokenUtil tokenUtil, JwtConstants constants) {

		this.tokenUtil = tokenUtil;
		this.constants = constants;
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {

		return isUnprotectedUrl.test(request.getRequestURI());
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		createSessionFrom(request.getHeader(AUTHORIZATION));

		filterChain.doFilter(request, response);
	}

	private void createSessionFrom(String authorizationHeader) {

		tokenUtil.validateBearer(authorizationHeader);

		DecodedJWT decodedJWT = tokenUtil.extractBearer.apply(authorizationHeader);

		tokenUtil.validateTokenExpiration(decodedJWT);

		String username = decodedJWT.getSubject();
		String[] roles = decodedJWT.getClaim(constants.getClaims().get("roles")).asArray(String.class);

		List<SimpleGrantedAuthority> authorities = roles != null ? Stream.of(roles)
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList()) : new ArrayList<>();

		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(username, null, authorities);

		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	}
}
