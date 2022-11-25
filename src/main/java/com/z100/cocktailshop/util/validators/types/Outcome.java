package com.z100.cocktailshop.util.validators.types;

public enum Outcome {
	ACCEPT, REJECT;

	public boolean isAccept() {
		return this == ACCEPT;
	}

	public boolean isReject() {
		return this == REJECT;
	}
}
