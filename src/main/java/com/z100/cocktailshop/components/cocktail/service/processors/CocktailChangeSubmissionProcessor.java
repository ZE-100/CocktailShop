package com.z100.cocktailshop.components.cocktail.service.processors;

import com.z100.cocktailshop.components.cocktail.dto.CocktailInDTO;
import com.z100.cocktailshop.components.cocktail.entity.Cocktail;
import com.z100.cocktailshop.components.cocktail.repository.CocktailRepository;
import com.z100.cocktailshop.components.cocktail.service.mapper.CocktailMapper;
import com.z100.cocktailshop.components.cocktail.service.validation.CocktailChangeSubmissionValidator;
import com.z100.cocktailshop.exceptions.ApiException;
import com.z100.cocktailshop.util.SubmissionProcessor;
import com.z100.cocktailshop.util.validators.ValidationResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CocktailChangeSubmissionProcessor extends SubmissionProcessor<CocktailInDTO, Cocktail> {

	private final CocktailChangeSubmissionValidator cocktailChangeSubmissionValidator;

	private final CocktailRepository cocktailRepository;

	private final CocktailMapper cocktailMapper;

	private Cocktail cocktailFromDb;

	@Getter
	private Cocktail updatedCocktail;

	@Override
	protected ValidationResult validate(CocktailInDTO cocktailIn) {
		return cocktailChangeSubmissionValidator.validate(cocktailIn);
	}

	@Override
	protected void persist(Cocktail newCocktail) {

		cocktailMapper.updateEntity(cocktailFromDb, newCocktail);

		updatedCocktail = cocktailRepository.save(cocktailFromDb);
	}

	@Override
	protected Cocktail mapSubmissionToEntity(CocktailInDTO cocktailInDTO) {
		return cocktailFromDb;
	}

	@Override
	protected void pre(CocktailInDTO cocktailIn) {

		cocktailFromDb = cocktailRepository.findById(cocktailIn.getId())
				.orElseThrow(() -> new ApiException("Cocktail not found"));
	}

	@Override
	protected void post(CocktailInDTO cocktailIn) {
		if (updatedCocktail == null) {
			throw new ApiException("Cocktail not updated");
		}
	}
}
