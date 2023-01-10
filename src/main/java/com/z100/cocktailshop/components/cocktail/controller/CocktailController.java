package com.z100.cocktailshop.components.cocktail.controller;

import com.z100.cocktailshop.components.cocktail.dto.CocktailInDTO;
import com.z100.cocktailshop.components.cocktail.service.crud.CocktailService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
public class CocktailController {

	private final CocktailService cocktailService;

	@PostMapping
	public ResponseEntity<?> create(@ModelAttribute CocktailInDTO cocktailIn) {

		return ResponseEntity.ok(cocktailService.save(cocktailIn));
	}

	@GetMapping("/cocktail/{id}")
	public ResponseEntity<?> get(@PathVariable String id) {

		return ResponseEntity.ok(cocktailService.get(Long.valueOf(id)));
	}

	@GetMapping("/cocktail")
	public ResponseEntity<?> getAll(@ModelAttribute String username) {

		return ResponseEntity.ok(cocktailService.getAllFrom(username));
	}

	@PutMapping("/cocktail/{id}")
	public ResponseEntity<?> update(@ModelAttribute CocktailInDTO userChangeIn) {

		return ResponseEntity.ok(cocktailService.update(userChangeIn));
	}

	@DeleteMapping("/cocktail/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		return ResponseEntity.ok(cocktailService.delete(id));
	}
}
