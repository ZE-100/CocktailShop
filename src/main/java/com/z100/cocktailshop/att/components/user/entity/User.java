package com.z100.cocktailshop.att.components.user.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.z100.cocktailshop.att.components.role.entity.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "preferences")
	private String preferences;

	@OneToMany(cascade = {CascadeType.ALL},
			orphanRemoval = true,
			mappedBy = "user")
	@JsonBackReference
	private List<Role> roles;
}
