package pl.vgtworld.budget.app.receiptcreate.validators.newproduct;

import pl.vgtworld.budget.core.validation.AbstractResult;
import pl.vgtworld.budget.services.dto.products.NewProduct;

import java.util.List;

public class ValidationResult extends AbstractResult {

	private NewProduct product;

	public ValidationResult(List<String> errors, NewProduct product) {
		super(errors);
		this.product = product;
	}

	@Override
	public boolean isValid() {
		return super.isValid() && product != null;
	}

}
