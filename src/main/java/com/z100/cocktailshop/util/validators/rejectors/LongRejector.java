package com.z100.cocktailshop.util.validators.rejectors;

import com.z100.cocktailshop.util.validators.Pass;
import com.z100.cocktailshop.util.validators.collectors.ReasonCollector;

public class LongRejector extends BaseRejector<Long> {

	public LongRejector(Pass pass, Long entry) {
		super(pass, entry);
	}

	public ReasonCollector ifNegative() {

		if (entry < 0) {
			this.reject();
		}

		return this.pass;
	}

	public ReasonCollector ifPositive() {

		if (entry >= 0) {
			this.reject();
		}

		return this.pass;
	}
}
