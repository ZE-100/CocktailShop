package com.z100.cocktailshop.components.user.controller;

import com.z100.cocktailshop.components.user.dto.UserInDTO;
import com.z100.cocktailshop.components.user.dto.UserOutDTO;
import com.z100.cocktailshop.components.user.service.crud.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {

	private final IUserService userService;

	@GetMapping("/login")
	public String loginView(Model model) {

		model.addAttribute("userIn", new UserInDTO());

//		model.addAttribute("loginError", true);

		return "auth/login";
	}

	@PostMapping("/auth/login")
	public String login(@ModelAttribute UserInDTO userDTO, Model model) {

		model.addAttribute("errorMessage", userService.get(1L));

		return "/misc/error";
	}

	@GetMapping("/register")
	public String registerView(Model model) {

		model.addAttribute("userIn", new UserInDTO());

//		if (true)
//			throw new ApiException("Sananas");

		return "auth/register";
	}

	@PostMapping("/auth/register")
	public String register(@ModelAttribute UserInDTO userIn, Model model) {

		UserOutDTO register = userService.register(userIn);

		model.addAttribute("errorMessage", register.getUsername());

		return "misc/error.html";
	}

	@PutMapping("/user/update")
	public ResponseEntity<?> update(@ModelAttribute UserInDTO userIn) {

		return ResponseEntity.ok(userService.update(userIn));
	}

	@DeleteMapping("/user/delete")
	public ResponseEntity<?> delete(@ModelAttribute Long id) {

		return ResponseEntity.ok(userService.delete(id));
	}
}
