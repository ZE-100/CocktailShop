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

	@PostMapping("/auth/login")
	public String login(@ModelAttribute UserInDTO userDTO, Model model) {

		model.addAttribute("errorMessage", userService.get(1L));

		return "/misc/error";
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
