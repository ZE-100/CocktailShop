package com.z100.cocktailshop.components.user.service.mapper;

import com.z100.cocktailshop.components.cocktail.entity.Cocktail;
import com.z100.cocktailshop.components.user.dto.UserInDTO;
import com.z100.cocktailshop.components.user.dto.UserOutDTO;
import com.z100.cocktailshop.components.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Mapper(componentModel = "spring")
public abstract class UserMapper {

	protected final PasswordEncoder passwordEncoder;

	public abstract UserOutDTO entityToOutDTO(User user);

	@Mapping(target = "password", qualifiedByName = "hashPassword")
	public abstract User inDTOToEntity(UserInDTO userIn);

	public abstract void updateEntity(User source, @MappingTarget User target);

	@Named("hashPassword")
	public String hashPassword(String password) {
		return passwordEncoder.encode(password);
	}
}
