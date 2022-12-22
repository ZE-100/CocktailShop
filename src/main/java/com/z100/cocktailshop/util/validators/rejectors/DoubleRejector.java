package com.z100.cocktailshop.util.validators.rejectors;

import com.z100.cocktailshop.util.validators.Pass;
import com.z100.cocktailshop.util.validators.collectors.ReasonCollector;

public class DoubleRejector extends BaseRejector<Double> {

	public DoubleRejector(Pass pass, Double entry) {
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
