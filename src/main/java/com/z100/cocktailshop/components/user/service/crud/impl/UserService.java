package com.z100.cocktailshop.components.user.service.crud.impl;

import com.z100.cocktailshop.components.user.dto.UserInDTO;
import com.z100.cocktailshop.components.user.dto.UserOutDTO;
import com.z100.cocktailshop.components.user.entity.User;
import com.z100.cocktailshop.components.user.repository.UserRepository;
import com.z100.cocktailshop.components.user.service.crud.IUserService;
import com.z100.cocktailshop.components.user.service.mapper.UserMapper;
import com.z100.cocktailshop.components.user.service.processors.UserChangeSubmissionProcessor;
import com.z100.cocktailshop.components.user.service.processors.UserSubmissionProcessor;
import com.z100.cocktailshop.exceptions.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.StreamSupport;

@Component
@AllArgsConstructor
public class UserService implements IUserService {

	private final UserRepository userRepository;

	private final UserMapper userMapper;

	private final UserSubmissionProcessor submissionProcessor;

	private final UserChangeSubmissionProcessor changeSubmissionProcessor;

	@Override
	@Transactional(rollbackOn = ApiException.class)
	public UserOutDTO register(UserInDTO userIn) {

		submissionProcessor.process(userIn);

		return userMapper.entityToOutDTO(submissionProcessor.getSavedUser());
	}

	@Override
	public UserOutDTO get(Long id) {

		User user = userRepository.findById(id)
				.orElseThrow(() -> new ApiException("User with ID not found"));

		return userMapper.entityToOutDTO(user);
	}

	@Override
	public List<UserOutDTO> getAll() {

		return StreamSupport.stream(userRepository.findAll().spliterator(), false)
				.map(userMapper::entityToOutDTO)
				.toList();
	}

	@Override
	@Transactional(rollbackOn = ApiException.class)
	public UserOutDTO update(UserInDTO userIn) {

		changeSubmissionProcessor.process(userIn);

		return userMapper.entityToOutDTO(changeSubmissionProcessor.getUpdatedUser());
	}

	@Override
	@Transactional(rollbackOn = ApiException.class)
	public Boolean delete(Long id) {

		User user = userRepository.findById(id)
				.orElseThrow(() -> new ApiException("User not found"));

		userRepository.delete(user);

		return userRepository.findById(id).isEmpty();
	}
}
