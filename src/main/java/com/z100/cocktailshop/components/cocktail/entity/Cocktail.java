package com.z100.cocktailshop.components.cocktail.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.z100.cocktailshop.components.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "cocktail")
public class Cocktail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "picture")
	private String picture; //TODO

	@Column(name = "price")
	private Double price;

	@ManyToOne
	@JoinColumn(name = "user")
	@JsonManagedReference
	private User user;
}
