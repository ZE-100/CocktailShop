package com.z100.cocktailshop.components.user.service.mapper;

import com.z100.cocktailshop.components.user.dto.UserInDTO;
import com.z100.cocktailshop.components.user.dto.UserOutDTO;
import com.z100.cocktailshop.components.user.entity.User;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class UserMapper {

	@Autowired
	protected PasswordEncoder passwordEncoder;

	public abstract UserOutDTO entityToOutDTO(User user);

	@Mapping(target = "password", ignore = true)
	public abstract User inDTOToEntity(UserInDTO userIn);

	@Mappings({
			@Mapping(target = "roles", ignore = true),
			@Mapping(target = "cocktails", ignore = true)
	})
	public abstract void updateEntity(User source, @MappingTarget User target);

	@AfterMapping
	public void hashPassword(UserInDTO source, @MappingTarget User target) {
		target.setPassword(passwordEncoder.encode(source.getPassword()));
	}
}
