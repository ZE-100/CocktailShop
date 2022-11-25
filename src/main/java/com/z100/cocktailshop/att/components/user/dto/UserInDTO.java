package com.z100.cocktailshop.att.components.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.z100.cocktailshop.att.util.communication.InDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInDTO extends InDTO {

	private String username;

	private String email;

	private String password;
}
