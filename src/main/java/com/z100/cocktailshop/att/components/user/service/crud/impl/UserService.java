package com.z100.cocktailshop.att.components.user.service.crud.impl;

import com.z100.cocktailshop.att.components.user.dto.UserChangeInDTO;
import com.z100.cocktailshop.att.components.user.dto.UserInDTO;
import com.z100.cocktailshop.att.components.user.dto.UserOutDTO;
import com.z100.cocktailshop.att.components.user.entity.User;
import com.z100.cocktailshop.att.components.user.repository.UserRepository;
import com.z100.cocktailshop.att.components.user.service.crud.IUserService;
import com.z100.cocktailshop.att.components.user.service.mapper.UserMapper;
import com.z100.cocktailshop.att.exceptions.ApiException;
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

		return userMapper.entityToOutDTO(userRepository.save(user).orElseThrow(ApiException::new));
	}

	@Override
	public Boolean update(UserChangeInDTO userIn) {

		User user = userMapper.changeInDTOToEntity(userIn);

		return userRepository.save(user).isPresent();
	}

	@Override
	public Boolean delete(String username) {

		User user = userRepository.findByUsername(username).orElseThrow(ApiException::new);

		userRepository.delete(user);

		return userRepository.findByUsername(username).isEmpty();
	}
}
