package com.z100.cocktailshop.att.components.user.service.mapper;

import com.sun.istack.NotNull;
import com.z100.cocktailshop.att.components.user.dto.UserChangeInDTO;
import com.z100.cocktailshop.att.components.user.dto.UserInDTO;
import com.z100.cocktailshop.att.components.user.dto.UserOutDTO;
import com.z100.cocktailshop.att.components.user.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

	public abstract UserOutDTO entityToOutDTO(@NotNull User user);

	public abstract User inDTOToEntity(UserInDTO userIn);

	public abstract User changeInDTOToEntity(UserChangeInDTO userIn);
}
