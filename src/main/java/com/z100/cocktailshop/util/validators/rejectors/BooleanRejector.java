package com.z100.cocktailshop.util.validators.rejectors;

import com.z100.cocktailshop.util.validators.Pass;
import com.z100.cocktailshop.util.validators.collectors.ReasonCollector;

public class BooleanRejector extends BaseRejector<Boolean> {

	public BooleanRejector(Pass pass, Boolean entry) {
		super(pass, entry);
	}

	public ReasonCollector ifTrue() {

		if (Boolean.TRUE.equals(entry)) {
			this.reject();
		} else {
			this.accept();
		}

		return this.pass;
	}

	public ReasonCollector ifFalse() {

		if (Boolean.FALSE.equals(entry)) {
			this.reject();
		} else {
			this.accept();
		}

		return this.pass;
	}
}
