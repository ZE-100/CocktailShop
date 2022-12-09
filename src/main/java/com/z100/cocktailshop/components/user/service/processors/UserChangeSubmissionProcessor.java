package com.z100.cocktailshop.components.user.service.processors;

import com.z100.cocktailshop.components.user.dto.UserChangeInDTO;
import com.z100.cocktailshop.components.user.entity.User;
import com.z100.cocktailshop.components.user.repository.UserRepository;
import com.z100.cocktailshop.components.user.service.validation.UserChangeSubmissionValidator;
import com.z100.cocktailshop.exceptions.*;
import com.z100.cocktailshop.util.SubmissionProcessor;
import com.z100.cocktailshop.util.validators.ValidationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserChangeSubmissionProcessor extends SubmissionProcessor<UserChangeInDTO> {

	private final UserChangeSubmissionValidator userChangeSubmissionValidator;

	private final UserRepository userRepository;

	@Override
	protected ValidationResult validate(UserChangeInDTO userIn) {
		return userChangeSubmissionValidator.validate(userIn);
	}

	@Override
	protected void persist(UserChangeInDTO userIn) {

		User user = userRepository.findByUsername("")
				.orElseThrow(() -> new ApiException("User to edit not found"));

		user.setEmail(userIn.getNewEmail());
		user.setPassword(userIn.getNewPassword());

		userRepository.save(user);
	}

	@Override
	protected void prePersistOperations(UserChangeInDTO userIn) {
		if (userRepository.findByEmail(userIn.getNewEmail()).isPresent()) {
			throw new ApiException("Email already exists");
		}
	}

	@Override
	protected void postPersistOperations(UserChangeInDTO userIn) {
		if (userRepository.findByEmailAndPassword(userIn.getNewEmail(), userIn.getNewPassword()).isEmpty()) {
			throw new ApiException("User not updated correctly");
		}
	}
}
