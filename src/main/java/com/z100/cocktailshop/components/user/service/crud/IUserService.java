package com.z100.cocktailshop.components.user.service.crud;

import com.z100.cocktailshop.components.user.dto.UserInDTO;
import com.z100.cocktailshop.components.user.dto.UserOutDTO;
import com.z100.cocktailshop.exceptions.*;

import javax.transaction.Transactional;
import java.util.List;

public interface IUserService {

	UserOutDTO register(UserInDTO user);

	UserOutDTO get(Long id);

	List<UserOutDTO> getAll();

	UserOutDTO update(UserInDTO user);

	Boolean delete(Long id);
}
