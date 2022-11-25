package com.z100.cocktailshop.components.user.service.crud.impl;

import com.z100.cocktailshop.components.user.dto.UserChangeInDTO;
import com.z100.cocktailshop.components.user.dto.UserInDTO;
import com.z100.cocktailshop.components.user.dto.UserOutDTO;
import com.z100.cocktailshop.components.user.entity.User;
import com.z100.cocktailshop.components.user.repository.UserRepository;
import com.z100.cocktailshop.components.user.service.crud.IUserService;
import com.z100.cocktailshop.components.user.service.mapper.UserMapper;
import com.z100.cocktailshop.exceptions.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserService implements IUserService {

	private final UserRepository userRepository;

	private final UserMapper userMapper;

	@Override
	public UserOutDTO register(UserInDTO inDTO) {

		User user = userMapper.inDTOToEntity(inDTO);

		return userMapper.entityToOutDTO(userRepository.save(user));
	}

	@Override
	public Boolean update(UserChangeInDTO userIn) {

		User user = userMapper.changeInDTOToEntity(userIn);

		return userRepository.save(user) != null;
	}

	@Override
	public Boolean delete(String username) {

		User user = userRepository.findByUsername(username).orElseThrow(ApiException::new);

		userRepository.delete(user);

		return userRepository.findByUsername(username).isEmpty();
	}
}
