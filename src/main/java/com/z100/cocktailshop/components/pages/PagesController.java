package com.z100.cocktailshop.components.pages;

import com.z100.cocktailshop.components.user.dto.UserInDTO;
import com.z100.cocktailshop.components.user.service.crud.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class PagesController  {

	private final IUserService userService;

	@GetMapping("/")
	public String index(Model model) {

		return "index";
	}

	@PostMapping("/shop")
	public String shop(Model model) {

		return "/shop/buy";
	}
}
