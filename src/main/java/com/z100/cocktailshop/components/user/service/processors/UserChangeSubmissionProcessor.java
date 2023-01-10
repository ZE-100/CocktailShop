package com.z100.cocktailshop.components.user.service.processors;

import com.z100.cocktailshop.components.user.dto.UserInDTO;
import com.z100.cocktailshop.components.user.entity.User;
import com.z100.cocktailshop.components.user.repository.IUserRepository;
import com.z100.cocktailshop.components.user.service.mapper.UserMapper;
import com.z100.cocktailshop.components.user.service.validation.UserChangeSubmissionValidator;
import com.z100.cocktailshop.exceptions.*;
import com.z100.cocktailshop.util.SubmissionProcessor;
import com.z100.cocktailshop.util.validators.ValidationResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserChangeSubmissionProcessor extends SubmissionProcessor<UserInDTO, User> {

	private final UserChangeSubmissionValidator userChangeSubmissionValidator;

	private final IUserRepository userRepository;

	private final UserMapper userMapper;

	private User userFromDb;

	@Getter
	private User updatedUser;

	@Override
	protected ValidationResult validate(UserInDTO submission) {
		return userChangeSubmissionValidator.validate(submission);
	}

	@Override
	protected void persist(User submission) {

		userMapper.updateEntity(userFromDb, submission);

		updatedUser = userRepository.save(userFromDb);
	}

	@Override
	protected void pre(UserInDTO userIn) {

		if (userRepository.findByEmail(userIn.getEmail()).isPresent())
			throw new ApiException("Email already exists");

		userFromDb = userRepository.findById(userIn.getId())
				.orElseThrow(() -> new ApiException("User doesn't exist"));
	}

	@Override
	protected User mapSubmissionToEntity(UserInDTO userIn) {

		return userMapper.inDTOToEntity(userIn);
	}

	@Override
	protected void post(UserInDTO userIn) {

		if (userRepository.findByUsername(userIn.getUsername()).isEmpty())
			throw new ApiException("User not updated");
	}
}
