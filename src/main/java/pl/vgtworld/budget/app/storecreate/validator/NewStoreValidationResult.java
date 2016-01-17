package pl.vgtworld.budget.app.storecreate.validator;

import pl.vgtworld.budget.core.validation.AbstractResult;
import pl.vgtworld.budget.services.dto.stores.NewStore;

import java.util.List;

public class NewStoreValidationResult extends AbstractResult {

	private NewStore store;

	public NewStoreValidationResult(List<String> errors, NewStore store) {
		super(errors);
		this.store = store;
	}

	public NewStore getStore() {
		return store;
	}

	@Override
	public boolean isValid() {
		return super.isValid() && store != null;
	}

}
