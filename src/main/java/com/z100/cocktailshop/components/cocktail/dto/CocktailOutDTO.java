package com.z100.cocktailshop.components.cocktail.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CocktailOutDTO {

	private Long id;

	private String name;

	private String description;

	private String picture; //TODO

	private Double price;

	private String username;
}
