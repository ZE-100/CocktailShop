package com.z100.cocktailshop.util.validators.rejectors;

import com.z100.cocktailshop.util.validators.Pass;
import com.z100.cocktailshop.util.validators.collectors.ReasonCollector;

import java.util.regex.Pattern;

public class StringRejector extends BaseRejector<String> {

	public StringRejector(Pass pass, String entry) {
		super(pass, entry);
	}

	public ReasonCollector ifEmpty() {

		if (entry == null || entry.isEmpty()) {
			this.reject();
		} else {
			this.accept();
		}

		return this.pass;
	}
}
