package com.z100.cocktailshop.components.cocktail.repository;

import com.z100.cocktailshop.components.cocktail.entity.Cocktail;
import com.z100.cocktailshop.components.user.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CocktailRepository extends CrudRepository<Cocktail, Long> {

	List<Cocktail> findAllByUser(User user);

	List<Cocktail> findAllByUserUsername(String username);
}
