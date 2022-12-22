package com.z100.cocktailshop.components.cocktail.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.z100.cocktailshop.components.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CocktailInDTO {

	private Long id;

	private String name;

	private String description;

	private String picture; //TODO

	private Double price;
}
