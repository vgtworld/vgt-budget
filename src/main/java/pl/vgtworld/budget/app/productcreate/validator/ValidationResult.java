package pl.vgtworld.budget.app.productcreate.validator;

import pl.vgtworld.budget.app.productcreate.dto.NewProductWithTags;
import pl.vgtworld.budget.core.validation.AbstractResult;

import java.util.List;

public class ValidationResult extends AbstractResult {

	private NewProductWithTags product;

	public ValidationResult(List<String> errors, NewProductWithTags product) {
		super(errors);
		this.product = product;
	}

	public NewProductWithTags getProduct() {
		return product;
	}

	@Override
	public boolean isValid() {
		return super.isValid() && product != null;
	}

}
