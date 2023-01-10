package com.z100.cocktailshop.server.security.services;

import com.z100.cocktailshop.components.user.entity.User;
import com.z100.cocktailshop.components.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

	private final IUserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> potentialUser = userRepo.findByUsername(username);

		User user = potentialUser.isEmpty() ? loadByEmail(username) : potentialUser.get();

		log.debug("User found: {}", username);

		List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}

	private User loadByEmail(String email) throws UsernameNotFoundException {

		// Exception only thrown here, as this is executed anyway.
		return userRepo.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}
}
