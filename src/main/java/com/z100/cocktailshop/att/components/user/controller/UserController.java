package com.z100.cocktailshop.att.components.user.controller;

import com.z100.cocktailshop.att.components.user.dto.UserChangeInDTO;
import com.z100.cocktailshop.att.components.user.dto.UserInDTO;
import com.z100.cocktailshop.att.components.user.service.crud.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class UserController {

	private final IUserService userService;

	@GetMapping("/login")
	public String loginView(Model model) {

		model.addAttribute("userDTO", new UserInDTO());

		return "auth/login";
	}

	@GetMapping("/register")
	public String registerView(Model model) {

		model.addAttribute("userDTO", new UserInDTO());

		return "auth/register";
	}

	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@ModelAttribute UserInDTO userDTO) {

		//TODO: Add login
		return ResponseEntity.ok("Login süccess");
	}

	@PostMapping("/auth/register")
	public ResponseEntity<?> register(@ModelAttribute UserInDTO userIn) {

		return ResponseEntity.ok(userService.register(userIn));
	}

	@PutMapping("/user/update")
	public ResponseEntity<?> update(@ModelAttribute UserChangeInDTO userChangeIn) {

		return ResponseEntity.ok(userService.update(userChangeIn));
	}

	@DeleteMapping("/user/delete")
	public ResponseEntity<?> delete(@ModelAttribute String username) {

		return ResponseEntity.ok(userService.delete(username));
	}
}
