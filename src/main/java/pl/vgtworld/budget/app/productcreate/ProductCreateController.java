package pl.vgtworld.budget.app.productcreate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.budget.app.productcreate.dto.NewProductForm;
import pl.vgtworld.budget.app.productcreate.validator.NewProductValidator;
import pl.vgtworld.budget.app.productcreate.validator.ValidationResult;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class ProductCreateController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductCreateController.class);

	private NewProductForm product = new NewProductForm();

	private List<String> submitErrors = new ArrayList<>();

	@EJB
	private ProductCreateService productCreateService;

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

	public String submitForm() {
		LOGGER.debug("Submitted new product form: {}", product);
		NewProductValidator validator = new NewProductValidator();
		ValidationResult result = validator.validate(product);
		if (result.isValid()) {
			productCreateService.createNewProduct(result.getProduct());
			return "product-create-success.xhtml";
		} else {
			submitErrors = result.getErrors();
			return "product-create.xhtml";
		}
	}

}
