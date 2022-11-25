package com.z100.cocktailshop.components.user.service.mapper;

import com.z100.cocktailshop.components.user.dto.UserChangeInDTO;
import com.z100.cocktailshop.components.user.dto.UserInDTO;
import com.z100.cocktailshop.components.user.dto.UserOutDTO;
import com.z100.cocktailshop.components.user.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

	public abstract UserOutDTO entityToOutDTO(User user);

	public abstract User inDTOToEntity(UserInDTO userIn);

	public abstract User changeInDTOToEntity(UserChangeInDTO userIn);
}
