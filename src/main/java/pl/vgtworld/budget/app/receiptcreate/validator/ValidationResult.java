package pl.vgtworld.budget.app.receiptcreate.validator;

import pl.vgtworld.budget.core.validation.AbstractResult;

import java.util.List;

public class ValidationResult extends AbstractResult {

	public ValidationResult(List<String> errors) {
		super(errors);
	}

	@Override
	public boolean isValid() {
		return super.isValid();
	}

}
