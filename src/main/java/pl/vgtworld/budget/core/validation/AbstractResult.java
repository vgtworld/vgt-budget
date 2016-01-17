package pl.vgtworld.budget.core.validation;

import java.util.List;

public abstract class AbstractResult {

	private List<String> errors;

	public AbstractResult(List<String> errors) {
		this.errors = errors;
	}

	public List<String> getErrors() {
		return errors;
	}

	public boolean isValid() {
		return errors.isEmpty();
	}

}
