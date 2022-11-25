package com.z100.cocktailshop.att.components.role.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.z100.cocktailshop.att.components.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "user")
	@JsonManagedReference
	private User user;
}
