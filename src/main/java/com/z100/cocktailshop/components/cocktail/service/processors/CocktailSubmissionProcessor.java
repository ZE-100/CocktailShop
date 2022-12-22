package com.z100.cocktailshop.components.cocktail.service.processors;

import com.z100.cocktailshop.components.cocktail.dto.CocktailInDTO;
import com.z100.cocktailshop.components.cocktail.entity.Cocktail;
import com.z100.cocktailshop.components.cocktail.repository.CocktailRepository;
import com.z100.cocktailshop.components.cocktail.service.mapper.CocktailMapper;
import com.z100.cocktailshop.components.cocktail.service.validation.CocktailSubmissionValidator;
import com.z100.cocktailshop.exceptions.ApiException;
import com.z100.cocktailshop.util.SubmissionProcessor;
import com.z100.cocktailshop.util.validators.ValidationResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CocktailSubmissionProcessor extends SubmissionProcessor<CocktailInDTO, Cocktail> {

	private final CocktailSubmissionValidator cocktailSubmissionValidator;

	private final CocktailRepository cocktailRepository;

	private final CocktailMapper cocktailMapper;

	@Getter
	private Cocktail savedCocktail;

	@Override
	protected ValidationResult validate(CocktailInDTO cocktailIn) {
		return cocktailSubmissionValidator.validate(cocktailIn);
	}

	@Override
	protected void persist(Cocktail cocktail) {

		savedCocktail = cocktailRepository.save(cocktail);
	}

	@Override
	protected void pre(CocktailInDTO cocktailIn) {
	}

	@Override
	protected Cocktail mapSubmissionToEntity(CocktailInDTO cocktailIn) {

		return cocktailMapper.inDTOToEntity(cocktailIn);
	}

	@Override
	protected void post(CocktailInDTO cocktailIn) {

		if (savedCocktail == null)
			throw new ApiException("Cocktail not persisted");
	}
}
