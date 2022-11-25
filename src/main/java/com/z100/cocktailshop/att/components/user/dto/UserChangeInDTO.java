package com.z100.cocktailshop.att.components.user.dto;

import com.z100.cocktailshop.att.util.communication.InDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserChangeInDTO extends InDTO {

	private String newEmail;

	private String newPassword;
}
