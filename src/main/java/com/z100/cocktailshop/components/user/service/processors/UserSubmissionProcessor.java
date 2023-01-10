package com.z100.cocktailshop.components.user.service.processors;

import com.z100.cocktailshop.components.user.dto.UserInDTO;
import com.z100.cocktailshop.components.user.entity.User;
import com.z100.cocktailshop.components.user.repository.IUserRepository;
import com.z100.cocktailshop.components.user.service.mapper.UserMapper;
import com.z100.cocktailshop.components.user.service.validation.UserSubmissionValidator;
import com.z100.cocktailshop.exceptions.*;
import com.z100.cocktailshop.util.SubmissionProcessor;
import com.z100.cocktailshop.util.validators.ValidationResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSubmissionProcessor extends SubmissionProcessor<UserInDTO, User> {

	private final UserSubmissionValidator userSubmissionValidator;

	private final IUserRepository userRepository;

	private final UserMapper userMapper;

	@Getter
	private User savedUser;

	@Override
	protected ValidationResult validate(UserInDTO userIn) {

		return userSubmissionValidator.validate(userIn);
	}

	@Override
	protected void persist(User submission) {

//		submission.setPassword();

		savedUser = userRepository.save(submission);
	}

	@Override
	protected void pre(UserInDTO userIn) {

		if (userRepository.findByUsername(userIn.getUsername()).isPresent())
			throw new ApiException("Username already exists");
	}

	@Override
	protected User mapSubmissionToEntity(UserInDTO userIn) {

		return userMapper.inDTOToEntity(userIn);
	}

	@Override
	protected void post(UserInDTO userIn) {

		if (userRepository.findByUsername(userIn.getUsername()).isEmpty())
			throw new ApiException("User not persisted correctly");
	}
}
