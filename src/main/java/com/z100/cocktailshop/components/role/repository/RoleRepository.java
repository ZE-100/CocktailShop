package com.z100.cocktailshop.components.role.repository;

import com.z100.cocktailshop.components.role.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

	Optional<Role> findRoleByName(String name);
}
