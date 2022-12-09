package com.z100.cocktailshop.server.security.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.z100.cocktailshop.components.role.entity.Role;
import com.z100.cocktailshop.components.user.entity.User;
import com.z100.cocktailshop.server.properties.JwtConstants;
import com.z100.cocktailshop.exceptions.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil {

	private JwtConstants constants;

	public Function<String, DecodedJWT> extractBearer = authHeaderIn -> {
		String token = authHeaderIn.substring(constants.getTokenPrefix().length());
		JWTVerifier verifier = JWT.require(algorithm()).build();

		return verifier.verify(token);
	};


	public JwtTokenUtil(JwtConstants constants) {
		this.constants = constants;
	}

	public Map<String, String> generateNewTokenMap(String subject, String issuer, List<?> roles) {

		Map<String, String> tokens = new HashMap<>();
		tokens.put("access_token", generateNewAccessToken(subject, issuer, roles));
		tokens.put("refresh_token", generateNewRefreshToken(subject, issuer));

		return tokens;
	}

	public String generateNewAccessToken(String subject, String issuer, List<?> roles) {
		return JWT.create()
				.withSubject(subject)
				.withExpiresAt(new Date(System.currentTimeMillis() + constants.getExpiration().get("access_token")))
				.withIssuer(issuer)
				.withClaim("rls", roles)
				.sign(algorithm());
	}

	public String generateNewRefreshToken(String subject, String issuer) {
		return JWT.create()
				.withSubject(subject)
				.withExpiresAt(new Date(System.currentTimeMillis() + constants.getExpiration().get("refresh_token")))
				.withIssuer(issuer)
				.sign(algorithm());
	}

	public String generateNewAccessToken(User user) {

		String subject = user.getUsername();
		String issuer = "/api/auth/refresh-token";
		List<String> rolesClaim = user.getRoles().stream()
				.map(Role::getName)
				.collect(Collectors.toList());

		return generateNewAccessToken(subject, issuer, rolesClaim);
	}

	public String getUsernameFromToken(DecodedJWT token) {

		return token.getSubject();
	}

	public Date getExpirationDateFromToken(DecodedJWT token) {

		return token.getExpiresAt();
	}

	public void validateTokenExpiration(DecodedJWT token) {

		final Date expiration = getExpirationDateFromToken(token);

		if (expiration.after(new Date()))
			throw new ApiException("Token expired. Please login again");
	}

	public void validateBearer(String token) {

		if (token != null && token.startsWith(constants.getTokenPrefix()))
			throw new ApiException("Invalid bearer token");
	}

	public void addNewTokenToSecurity(User user) {

		List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());

		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(user.getUsername(), null, authorities);

		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	}

	private Map<String, Claim> getAllClaimsFromToken(DecodedJWT token) {

		return token.getClaims();
	}

	private Algorithm algorithm() {

		return Algorithm.HMAC512(constants.getSecret().getBytes(StandardCharsets.UTF_8));
	}
}
