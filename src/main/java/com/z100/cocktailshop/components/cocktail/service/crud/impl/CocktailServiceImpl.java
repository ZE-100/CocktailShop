package com.z100.cocktailshop.components.cocktail.service.crud.impl;

import com.z100.cocktailshop.components.cocktail.dto.CocktailInDTO;
import com.z100.cocktailshop.components.cocktail.dto.CocktailOutDTO;
import com.z100.cocktailshop.components.cocktail.entity.Cocktail;
import com.z100.cocktailshop.components.cocktail.repository.CocktailRepository;
import com.z100.cocktailshop.components.cocktail.service.crud.CocktailService;
import com.z100.cocktailshop.components.cocktail.service.mapper.CocktailMapper;
import com.z100.cocktailshop.components.cocktail.service.processors.CocktailChangeSubmissionProcessor;
import com.z100.cocktailshop.components.cocktail.service.processors.CocktailSubmissionProcessor;
import com.z100.cocktailshop.exceptions.ApiException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.StreamSupport;

@Component
@AllArgsConstructor
public class CocktailServiceImpl implements CocktailService {

	private final CocktailRepository cocktailRepository;

	private final CocktailMapper cocktailMapper;

	private final CocktailChangeSubmissionProcessor changeSubmissionProcessor;

	private final CocktailSubmissionProcessor submissionProcessor;

	@Override
	@Transactional(rollbackOn = ApiException.class)
	public CocktailOutDTO save(CocktailInDTO cocktailIn) {

		submissionProcessor.process(cocktailIn);

		return cocktailMapper.entityToOutDTO(submissionProcessor.getSavedCocktail());
	}

	@Override
	public CocktailOutDTO get(Long id) {

		Cocktail cocktail = cocktailRepository.findById(id)
				.orElseThrow(() -> new ApiException("Cocktail with ID not found"));

		return cocktailMapper.entityToOutDTO(cocktail);
	}

	@Override
	public List<CocktailOutDTO> getAllFrom(String username) {

		return StreamSupport.stream(cocktailRepository.findAll().spliterator(), false)
				.map(cocktailMapper::entityToOutDTO)
				.toList();
	}

	@Override
	@Transactional(rollbackOn = ApiException.class)
	public CocktailOutDTO update(CocktailInDTO cocktailIn) {

		changeSubmissionProcessor.process(cocktailIn);

		return cocktailMapper.entityToOutDTO(changeSubmissionProcessor.getUpdatedCocktail());
	}

	@Override
	@Transactional(rollbackOn = ApiException.class)
	public Boolean delete(Long id) {

		Cocktail cocktail = cocktailRepository.findById(id)
				.orElseThrow(() -> new ApiException("Cocktail nonexistent"));

		cocktailRepository.delete(cocktail);

		return cocktailRepository.findById(id).isEmpty();
	}
}
