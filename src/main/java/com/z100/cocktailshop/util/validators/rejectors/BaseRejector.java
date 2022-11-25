package com.z100.cocktailshop.util.validators.rejectors;

import com.z100.cocktailshop.util.validators.Pass;
import com.z100.cocktailshop.util.validators.collectors.ReasonCollector;
import com.z100.cocktailshop.util.validators.types.Outcome;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseRejector<T> {

	protected Pass pass;

	protected T entry;

	public ReasonCollector ifNull() {

		if (entry == null) {
			reject();
		} else {
			accept();
		}

		return this.pass;
	}

	protected void accept() {
		this.pass.setOutcome(Outcome.ACCEPT);
	}

	protected void reject() {
		this.pass.setOutcome(Outcome.REJECT);
	}
}
