package com.z100.cocktailshop.util.communication;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public abstract class InDTO {

	private Map<String, String> headers;

	public String resolveHeaderByKey(String key) {
		return headers.get(key);
	}
}
