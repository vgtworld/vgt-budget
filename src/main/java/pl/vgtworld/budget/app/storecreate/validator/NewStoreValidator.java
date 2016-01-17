package pl.vgtworld.budget.app.storecreate.validator;

import pl.vgtworld.budget.app.storecreate.dto.NewStoreForm;
import pl.vgtworld.budget.services.dto.stores.NewStore;

import java.util.ArrayList;
import java.util.List;

public class NewStoreValidator {

	private static final int NAME_MAX_LENGTH = 100;
	private static final int CITY_MAX_LENGTH = 50;
	private static final int ADDRESS_MAX_LENGTH = 200;

	private static final String ERROR_NAME_REQUIRED = "Name is required.";
	private static final String ERROR_NAME_LENGTH = "Name max length is " + NAME_MAX_LENGTH + " characters.";
	private static final String ERROR_CITY_REQUIRED = "City is required.";
	private static final String ERROR_CITY_LENGTH = "City max length is " + CITY_MAX_LENGTH + " characters.";
	private static final String ERROR_ADDRESS_REQUIRED = "Address is required.";
	private static final String ERROR_ADDRESS_LENGTH = "Address max length is " + ADDRESS_MAX_LENGTH + " characters.";

	private List<String> errors = new ArrayList<>();

	public NewStoreValidationResult validate(NewStoreForm form) {
		errors = new ArrayList<>();
		validateName(form.getStoreName());
		validateCity(form.getStoreCity());
		validateAddress(form.getStoreAddress());
		if (errors.isEmpty()) {
			NewStore newStore = new NewStore();
			newStore.setName(form.getStoreName());
			newStore.setCity(form.getStoreCity());
			newStore.setAddress(form.getStoreAddress());
			return new NewStoreValidationResult(errors, newStore);
		}
		return new NewStoreValidationResult(errors, null);
	}

	private boolean validateName(String name) {
		if (name == null || name.isEmpty()) {
			errors.add(ERROR_NAME_REQUIRED);
			return false;
		}
		if (name.length() > NAME_MAX_LENGTH) {
			errors.add(ERROR_NAME_LENGTH);
			return false;
		}
		return true;
	}

	private boolean validateCity(String city) {
		if (city == null || city.isEmpty()) {
			errors.add(ERROR_CITY_REQUIRED);
			return false;
		}
		if (city.length() > CITY_MAX_LENGTH) {
			errors.add(ERROR_CITY_LENGTH);
			return false;
		}
		return true;
	}

	private boolean validateAddress(String address) {
		if (address == null || address.isEmpty()) {
			errors.add(ERROR_ADDRESS_REQUIRED);
			return false;
		}
		if (address.length() > ADDRESS_MAX_LENGTH) {
			errors.add(ERROR_ADDRESS_LENGTH);
			return false;
		}
		return true;
	}

}
