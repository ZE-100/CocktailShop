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

	@GetMapping("/login")
	public String loginView(Model model) {

		model.addAttribute("userIn", new UserInDTO());


		return "auth/login";
	}

	@GetMapping("/register")
	public String register(Model model) {

		model.addAttribute("userIn", new UserInDTO());

		return "auth/register";
	}

	@PostMapping("/shop")
	public String shop(Model model) {

		return "/shop/buy";
	}
}
