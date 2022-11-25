package com.z100.cocktailshop.att.components.role.service.mapper;

import com.z100.cocktailshop.att.components.role.entity.Role;
import com.z100.cocktailshop.att.components.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class RoleMapper {

	/**
	 * Get name of roles to display in frontend
	 *
	 * @param entity The user
	 * @return Displayable roles of user
	 */
	public List<String> authoritiesToRoles(User entity) {
		return entity.getRoles().stream()
				.map(Role::getName)
				.collect(Collectors.toList());
	}
}
