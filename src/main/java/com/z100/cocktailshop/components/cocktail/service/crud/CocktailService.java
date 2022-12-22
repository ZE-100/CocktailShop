package com.z100.cocktailshop.components.cocktail.service.crud;

import com.z100.cocktailshop.components.cocktail.dto.CocktailInDTO;
import com.z100.cocktailshop.components.cocktail.dto.CocktailOutDTO;

import java.util.List;

public interface CocktailService {

	CocktailOutDTO save(CocktailInDTO in);

	CocktailOutDTO get(Long id);

	List<CocktailOutDTO> getAllFrom(String username);

	CocktailOutDTO update(CocktailInDTO in);

	Boolean delete(Long id);
}
