package pl.vgtworld.budget.app.receiptcreate.validator;

import pl.vgtworld.budget.app.receiptcreate.dto.NewReceiptForm;
import pl.vgtworld.budget.app.receiptcreate.dto.ReceiptProduct;
import pl.vgtworld.budget.services.dto.products.ProductItem;
import pl.vgtworld.budget.services.dto.stores.StoreDto;
import pl.vgtworld.budget.services.storage.ProductService;
import pl.vgtworld.budget.services.storage.StoreService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewReceiptValidator {

	private StoreService storeService;

	private ProductService productService;

	private List<String> errors = new ArrayList<>();

	private static final String ERROR_STORE_REQUIRED = "Store is required.";
	private static final String ERROR_STORE_DOES_NOT_EXIST = "Store does not exist.";
	private static final String ERROR_PURCHASE_DATE_REQUIRED = "Purchase date is required.";
	private static final String ERROR_PRODUCTS_REQUIRED = "At least one product is required.";
	private static final String ERROR_PRODUCT_MISSING_ID = "Product does not exist.";
	private static final String ERROR_PRODUCT_AMOUNT_REQUIRED = "Amount is required for %s";
	private static final String ERROR_PRODUCT_PRICE_PER_UNIT_REQUIRED = "Price per unit is required for %s";

	public NewReceiptValidator(StoreService storeService, ProductService productService) {
		this.storeService = storeService;
		this.productService = productService;
	}

	public ValidationResult validate(NewReceiptForm receipt) {
		errors = new ArrayList<>();
		validateStore(receipt.getStore().getId());
		validatePurchaseDate(receipt.getPurchaseDate());
		validateProducts(receipt.getProducts());
		if (errors.isEmpty()) {
			//TODO Create dto for receipt service
			return new ValidationResult(errors); //TODO Add created dto.
		}
		return new ValidationResult(errors); //TODO Add null for dto.
	}

	private boolean validateStore(Integer id) {
		if (id == null) {
			errors.add(ERROR_STORE_REQUIRED);
			return false;
		}
		StoreDto store = storeService.findById(id);
		if (store == null) {
			errors.add(ERROR_STORE_DOES_NOT_EXIST);
			return false;
		}
		return true;
	}

	private boolean validatePurchaseDate(Date purchaseDate) {
		if (purchaseDate == null) {
			errors.add(ERROR_PURCHASE_DATE_REQUIRED);
			return false;
		}
		return true;
	}

	private boolean validateProducts(List<ReceiptProduct> products) {
		if (products == null || products.isEmpty()) {
			errors.add(ERROR_PRODUCTS_REQUIRED);
			return false;
		}
		boolean productsValidation = true;
		for (ReceiptProduct product : products) {
			boolean productValidation = validateProduct(product);
			if (!productValidation) {
				productsValidation = false;
			}

		}
		return productsValidation;
	}

	private boolean validateProduct(ReceiptProduct product) {
		if (product.getId() == null) {
			errors.add(ERROR_PRODUCT_MISSING_ID);
			return false;
		}
		ProductItem productItem = productService.findById(product.getId());
		if (productItem == null) {
			errors.add(ERROR_PRODUCT_MISSING_ID);
			return false;
		}
		boolean validationResult = true;
		Double amount = product.getAmount();
		if (amount == null || amount <= 0) {
			errors.add(String.format(ERROR_PRODUCT_AMOUNT_REQUIRED, productItem.getName()));
			validationResult = false;
		}
		Double pricePerUnit = product.getPricePerUnit();
		if (pricePerUnit == null || pricePerUnit <= 0) {
			errors.add(String.format(ERROR_PRODUCT_PRICE_PER_UNIT_REQUIRED, productItem.getName()));
			validationResult = false;
		}
		return validationResult;
	}

}
