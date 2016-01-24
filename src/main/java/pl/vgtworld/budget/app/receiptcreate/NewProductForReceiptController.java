package pl.vgtworld.budget.app.receiptcreate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.budget.app.receiptcreate.dto.NewProductForm;
import pl.vgtworld.budget.app.receiptcreate.validators.newproduct.NewProductValidator;
import pl.vgtworld.budget.app.receiptcreate.validators.newproduct.ValidationResult;
import pl.vgtworld.budget.services.ProductsService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class NewProductForReceiptController {

	private static final Logger LOGGER = LoggerFactory.getLogger(NewProductForReceiptController.class);

	private NewProductForm product = new NewProductForm();

	private List<String> submitErrors = new ArrayList<>();

	@EJB
	private ProductsService productsService;

	@ManagedProperty("#{receiptCreateController}")
	private ReceiptCreateController receiptCreateController;

	public NewProductForm getProduct() {
		return product;
	}

	public void setProduct(NewProductForm product) {
		this.product = product;
	}

	public List<String> getSubmitErrors() {
		return submitErrors;
	}

	public void setSubmitErrors(List<String> submitErrors) {
		this.submitErrors = submitErrors;
	}

	public ReceiptCreateController getReceiptCreateController() {
		return receiptCreateController;
	}

	public void setReceiptCreateController(ReceiptCreateController receiptCreateController) {
		this.receiptCreateController = receiptCreateController;
	}

	public void createProduct() {
		LOGGER.debug("Create new product for receipt. name={}, tags={}", product.getName(), product.getTags());
		NewProductValidator validator = new NewProductValidator();
		ValidationResult result = validator.validate(product);
		if (result.isValid()) {
			int productId = productsService.createNewProduct(result.getProduct());
			receiptCreateController.addProductToReceipt(productId, product.getName());
			this.product = new NewProductForm();
		} else {
			submitErrors = result.getErrors();
		}
	}

}
