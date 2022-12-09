package com.z100.cocktailshop.components.user.service.processors;

import com.z100.cocktailshop.components.user.dto.UserInDTO;
import com.z100.cocktailshop.components.user.entity.User;
import com.z100.cocktailshop.components.user.repository.UserRepository;
import com.z100.cocktailshop.components.user.service.mapper.UserMapper;
import com.z100.cocktailshop.components.user.service.validation.UserSubmissionValidator;
import com.z100.cocktailshop.exceptions.*;
import com.z100.cocktailshop.util.SubmissionProcessor;
import com.z100.cocktailshop.util.validators.ValidationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSubmissionProcessor extends SubmissionProcessor<UserInDTO> {

	private final UserSubmissionValidator userSubmissionValidator;

	private final UserRepository userRepository;

	private final UserMapper userMapper;

	private final PasswordEncoder passwordEncoder;

	public User getPersistedEntity() {
		return userRepository.findByUsername(submission.getUsername())
				.orElseThrow(() -> new ApiException("User not get-able, due to error"));
	}

	@Override
	protected ValidationResult validate(UserInDTO userIn) {
		return userSubmissionValidator.validate(userIn);
	}

	@Override
	protected void persist(UserInDTO userIn) {

		User user = userMapper.inDTOToEntity(userIn);

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		userRepository.save(user);
	}

	@Override
	protected void prePersistOperations(UserInDTO userIn) {
		if (userRepository.findByUsername(userIn.getUsername()).isPresent()) {
			throw new ApiException("Username already exists");
		}
	}

	@Override
	protected void postPersistOperations(UserInDTO userIn) {
		if (userRepository.findByUsername(userIn.getUsername()).isEmpty()) {
			throw new ApiException("User not persisted correctly");
		}
	}
}
