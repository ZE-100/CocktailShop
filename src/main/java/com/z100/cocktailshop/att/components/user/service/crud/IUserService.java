package com.z100.cocktailshop.att.components.user.service.crud;

import com.z100.cocktailshop.att.components.user.dto.UserChangeInDTO;
import com.z100.cocktailshop.att.components.user.dto.UserInDTO;
import com.z100.cocktailshop.att.components.user.dto.UserOutDTO;
import com.z100.cocktailshop.att.exceptions.ApiException;

import javax.transaction.Transactional;

public interface IUserService {

	@Transactional(rollbackOn = ApiException.class)
	UserOutDTO register(UserInDTO user);

	@Transactional(rollbackOn = ApiException.class)
	Boolean update(UserChangeInDTO user);

	@Transactional(rollbackOn = ApiException.class)
	Boolean delete(String username);
}
