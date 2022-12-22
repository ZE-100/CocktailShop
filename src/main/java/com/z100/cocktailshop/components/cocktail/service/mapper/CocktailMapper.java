package com.z100.cocktailshop.components.cocktail.service.mapper;

import com.z100.cocktailshop.components.cocktail.dto.CocktailInDTO;
import com.z100.cocktailshop.components.cocktail.dto.CocktailOutDTO;
import com.z100.cocktailshop.components.cocktail.entity.Cocktail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class CocktailMapper {

	public abstract CocktailOutDTO entityToOutDTO(Cocktail user);

	public abstract Cocktail inDTOToEntity(CocktailInDTO userIn);

	@Mapping(target = "id", ignore = true)
	public abstract void updateEntity(Cocktail source, @MappingTarget Cocktail target);
}
