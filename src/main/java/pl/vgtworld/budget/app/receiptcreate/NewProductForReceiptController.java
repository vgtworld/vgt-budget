package pl.vgtworld.budget.app.receiptcreate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.budget.app.receiptcreate.dto.NewProductForm;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class NewProductForReceiptController {

	private static final Logger LOGGER = LoggerFactory.getLogger(NewProductForReceiptController.class);

	private NewProductForm product = new NewProductForm();

	public NewProductForm getProduct() {
		return product;
	}

	public void setProduct(NewProductForm product) {
		this.product = product;
	}

	public void createProduct() {
		LOGGER.debug("Create new product for receipt. name={}, tags={}", product.getName(), product.getTags());
		//TODO Validate input.
		//TODO Create product in database.
		//TODO Add product to receipt.
		product = new NewProductForm();
	}

}
