package pl.vgtworld.budget.app.receiptcreate.validators.newproduct;

import pl.vgtworld.budget.app.receiptcreate.dto.NewProductForm;
import pl.vgtworld.budget.services.dto.products.NewProduct;

import java.util.ArrayList;
import java.util.List;

public class NewProductValidator {

	private static final int NAME_MAX_LENGTH = 100;
	private static final int TAG_MIN_LENGTH = 3;
	private static final int TAG_MAX_LENGTH = 100;

	private static final String ERROR_NAME_REQUIRED = "Name is required.";
	private static final String ERROR_NAME_LENGTH = "Name max length is " + NAME_MAX_LENGTH + " characters.";
	private static final String ERROR_TAG_MIN_LENGTH = "Tag min length is " + TAG_MIN_LENGTH + " characters.";
	private static final String ERROR_TAG_MAX_LENGTH = "Tag max length is " + TAG_MAX_LENGTH + " characters.";
	private static final String ERROR_TAG_MIN_COUNT = "There should be at least one tag added for product.";

	private List<String> errors = new ArrayList<>();

	public ValidationResult validate(NewProductForm product) {
		errors = new ArrayList<>();
		validateName(product.getName());
		List<String> extractedTags = extractTags(product.getTags());
		validateTags(extractedTags);
		if (errors.isEmpty()) {
			NewProduct newProduct = new NewProduct();
			newProduct.setName(product.getName());
			newProduct.setTags(extractedTags);
			return new ValidationResult(errors, newProduct);
		}
		return new ValidationResult(errors, null);
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

	private boolean validateTags(List<String> tags) {
		for (String tag : tags) {
			if (!validateTag(tag)) {
				return false;
			}
		}
		if (tags.isEmpty()) {
			errors.add(ERROR_TAG_MIN_COUNT);
			return false;
		}
		return true;
	}

	private boolean validateTag(String tag) {
		if (tag.length() < TAG_MIN_LENGTH) {
			errors.add(ERROR_TAG_MIN_LENGTH);
			return false;
		}
		if (tag.length() > TAG_MAX_LENGTH) {
			errors.add(ERROR_TAG_MAX_LENGTH);
			return false;
		}
		return true;
	}

	private List<String> extractTags(String input) {
		List<String> tags = new ArrayList<>();
		if (input != null && !input.isEmpty()) {
			for (String tag : input.split(",")) {
				tags.add(tag.trim());
			}
		}
		return tags;
	}

}
